--migration script for PostgreSQL 18 (lower versions won't work)

CREATE TYPE state_of_account AS ENUM(
    'active',
    'suspended',
    'deleted',
    'banned'
);

CREATE TYPE language AS ENUM(
    'eng'
):

CREATE TYPE display_mode AS ENUM(
    'light',
    'dark',
    'auto'
)

CREATE TYPE online_visibility AS ENUM(
   'visible',
   'not visible'
    --????
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
    state_of_account state_of_account NOT NULL DEFAULT 'active',

    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE UNIQUE INDEX uq_users_nickname ON users (LOWER(nickname)); --makes nickname unique and
-- treats upper and lowercase as equal


CREATE TABLE user_preferences(
    user_id UUID PRIMARY KEY DEFAULT uuidv7(),
    language language NOT NULL DEFAULT 'eng',
    email_notification_enabled BOOLEAN NOT NULL DEFAULT TRUE,
    display_mode display_mode NOT NULL DEFAULT 'auto',
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

CREATE TABLE user_hobby(
    user_id UUID,
    hobby_id UUID,

    CONSTRAINT pk_user_hobby PRIMARY KEY (user_id, hobby_id),
    CONSTRAINT fq_user_hobby_user_id FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE,
    CONSTRAINT fq_user_hobby_hobby_id FOREIGN KEY (hobby_id) REFERENCES hobby(id)
);