package hobist.backend.modules.profiles.domain.userProfiles;

import hobist.backend.modules.profiles.domain.userProfiles.valueObjects.Description;
import hobist.backend.modules.profiles.domain.userProfiles.valueObjects.Name;
import hobist.backend.modules.profiles.domain.userProfiles.valueObjects.Surname;
import hobist.backend.shared.domain.valueObjects.EntityId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_profiles")
public class UserProfiles {

    @EmbeddedId
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private EntityId userId;

    @Embedded
    @Column(nullable = true)
    private Name name;

    @Embedded
    @Column(nullable = true)
    private Surname surname;

    @Column(name="profile_image_path",nullable = true)
    private String profileImagePath;

    @Embedded
    @Column(nullable = true)
    private Description description;

    @Column(name = "number_of_posts", nullable = false)
    private int numberOfPosts = 0;

    @Column(name = "number_of_followers", nullable = false)
    private int numberOfFollowers = 0;

    @Column(name = "number_of_followed", nullable = false)
    private int numberOfFollowed = 0;

    @Column(name = "number_of_activities", nullable = false)
    private int numberOfActivities = 0;

    public UserProfiles(UUID userId, String name, String surname, String description) {
        this.userId = new EntityId(userId);
        this.name = new Name(name.strip());
        this.surname = new Surname(surname.strip());
        this.description = new Description(description);
    }
}
