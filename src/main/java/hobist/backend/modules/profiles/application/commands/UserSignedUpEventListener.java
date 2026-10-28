package hobist.backend.modules.profiles.application.commands;

import hobist.backend.modules.profiles.domain.userPreferences.UserPreferences;
import hobist.backend.modules.profiles.infrastructure.userPreferences.UserPreferencesRepository;
import hobist.backend.modules.users.domain.user.events.UserSignedUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSignedUpEventListener {

    private final UserPreferencesRepository userPreferencesRepository;

   @EventListener
    public void onUserSignedUp(UserSignedUpEvent event) {

    var userPreferences=new UserPreferences(event.userId());

    userPreferencesRepository.save(userPreferences);
   }

}