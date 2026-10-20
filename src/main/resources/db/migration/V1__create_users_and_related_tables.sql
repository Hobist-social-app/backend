--migration script for PostgreSQL 18 (lower versions won't work)

CREATE TYPE state_of_account AS ENUM(
    'active',
    'suspended',
    'deleted',
    'banned'
);

CREATE TYPE languages AS ENUM(
    'eng'
);

CREATE TYPE theme AS ENUM(
    'light',
    'dark',
    'auto'
);

CREATE TYPE online_visibility AS ENUM(
   'visible',
   'not_visible'
);

CREATE TYPE token_type AS ENUM(
    'password_reset',
    'email_verification'
);


CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT uuidv7(),
    nickname VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    last_login_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    state_of_account state_of_account NOT NULL DEFAULT 'active'

);

    CREATE UNIQUE INDEX uq_users_email ON users (LOWER(email));
    CREATE UNIQUE INDEX uq_users_nickname ON users (LOWER(nickname)); --makes nickname unique and
-- treats upper and lowercase as equal


CREATE TABLE user_preferences(
    user_id UUID PRIMARY KEY,
    language languages NOT NULL DEFAULT 'eng',
    email_notification_enabled BOOLEAN NOT NULL DEFAULT TRUE,
    push_notification_enabled BOOLEAN NOT NULL DEFAULT TRUE,
    notify_messages BOOLEAN NOT NULL DEFAULT TRUE,
    notify_comments BOOLEAN NOT NULL DEFAULT TRUE,
    notify_likes BOOLEAN NOT NULL DEFAULT TRUE,
    notify_follows BOOLEAN NOT NULL DEFAULT TRUE,
    theme theme NOT NULL DEFAULT 'auto',
    is_profile_private BOOLEAN NOT NULL DEFAULT TRUE,
    online_visibility online_visibility NOT NULL DEFAULT 'visible',

    CONSTRAINT fq_user_preferences_user_id
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE
);

CREATE TABLE user_profiles(
    user_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    profile_image_path TEXT,
    description VARCHAR(500),
    number_of_posts INTEGER NOT NULL DEFAULT 0 CHECK (number_of_posts >= 0),
    number_of_followers INTEGER NOT NULL DEFAULT 0 CHECK (number_of_followers >= 0),
    number_of_followed INTEGER NOT NULL DEFAULT 0 CHECK (number_of_followed >= 0),
    number_of_activities INTEGER NOT NULL DEFAULT 0 CHECK (number_of_activities >= 0),

    CONSTRAINT  fq_user_profiles_user_id
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE
);

CREATE TABLE hobby(
    id UUID PRIMARY KEY DEFAULT uuidv7(),
    name VARCHAR(255) NOT NULL

);
    CREATE INDEX idx_hobby_name ON hobby(name);

CREATE TABLE user_hobby(
    user_id UUID,
    hobby_id UUID,

    CONSTRAINT pk_user_hobby PRIMARY KEY (user_id, hobby_id),
    CONSTRAINT fq_user_hobby_user_id FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fq_user_hobby_hobby_id FOREIGN KEY (hobby_id) REFERENCES hobby(id)
        ON DELETE CASCADE
);
    CREATE INDEX idx_user_hobby_hobby_id ON user_hobby(hobby_id);


CREATE TABLE user_session(
    id UUID PRIMARY KEY DEFAULT uuidv7(),
    user_id UUID NOT NULL,
    hash VARCHAR(255) NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL,
    revoked_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    device_info TEXT,
    ip_address INET,

    CONSTRAINT fq_user_session_user_id FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

    CREATE INDEX idx_user_session_user_id ON user_session(user_id);


CREATE TABLE one_time_token(
    id UUID PRIMARY KEY DEFAULT uuidv7(),
    user_id UUID NOT NULL,
    hash VARCHAR(255) NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL,
    used_at TIMESTAMPTZ,
    token_type token_type NOT NULL,

    CONSTRAINT fq_one_time_token_user_id FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

    CREATE INDEX idx_one_time_token_user_id ON one_time_token(user_id);
