package hobist.backend.modules.profiles.domain.userPreferences;

import hobist.backend.modules.profiles.domain.userPreferences.enums.Language;
import hobist.backend.modules.profiles.domain.userPreferences.enums.OnlineVisibility;
import hobist.backend.modules.profiles.domain.userPreferences.enums.Theme;
import hobist.backend.shared.domain.valueObjects.EntityId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_preferences")
public class UserPreferences {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))//M.G: AttributeOverride name reason: "By default, JPA maps that embedded field to a column named id. But the user_preferences table calls it user_id."
    private EntityId userId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "language", nullable = false, columnDefinition = "languages")
    private Language language;

    @Column(name = "email_notification_enabled", nullable = false)
    private boolean emailNotificationEnabled;

    @Column(name = "push_notification_enabled", nullable = false)
    private boolean pushNotificationEnabled;

    @Column(name = "notify_messages", nullable = false)
    private boolean notifyMessages;

    @Column(name = "notify_comments", nullable = false)
    private boolean notifyComments;

    @Column(name = "notify_likes", nullable = false)
    private boolean notifyLikes;

    @Column(name = "notify_follows", nullable = false)
    private boolean notifyFollows;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "theme", nullable = false, columnDefinition = "theme")
    private Theme theme;

    @Column(name = "is_profile_private", nullable = false)
    private boolean profilePrivate;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "online_visibility", nullable = false, columnDefinition = "online_visibility")
    private OnlineVisibility onlineVisibility;

    public UserPreferences(EntityId userId) {
        this.userId = Objects.requireNonNull(userId, "User ID cannot be null!");
        this.language = Language.ENG;
        this.emailNotificationEnabled = true;
        this.pushNotificationEnabled = true;
        this.notifyMessages = true;
        this.notifyComments = true;
        this.notifyLikes = true;
        this.notifyFollows = true;
        this.theme = Theme.AUTO;
        this.profilePrivate = true;
        this.onlineVisibility = OnlineVisibility.VISIBLE;
    }
}
