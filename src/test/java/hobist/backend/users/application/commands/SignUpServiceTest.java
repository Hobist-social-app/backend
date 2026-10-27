package hobist.backend.users.application.commands;

import hobist.backend.modules.users.application.commands.signUp.SignUpService;
import hobist.backend.modules.users.application.dtos.signUp.SignUpRequestDto;
import hobist.backend.modules.users.domain.user.User;
import hobist.backend.modules.users.domain.user.enums.Role;
import hobist.backend.modules.users.domain.user.enums.StateOfAccount;
import hobist.backend.modules.users.domain.user.events.UserSignedUpEvent;
import hobist.backend.modules.users.infrastructure.user.UserRepository;
import hobist.backend.shared.domain.valueObjects.EntityId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.AbstractValidatingPasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private AbstractValidatingPasswordEncoder passwordEncoder;

    @InjectMocks
    private SignUpService signUpService;

    @Test
    void shouldSignUpUser(){
        var requestDto = new SignUpRequestDto("nickname123"
                ,"385957541300"
                ,"name@gmail.com"
                ,"password123");

        when(passwordEncoder.encode("password123"))
                .thenReturn("hashedpassword123");

        var timestampBefore =Instant.now();
        var responseDto= signUpService.signUp(requestDto);
        var timestampAfter =Instant.now();

        verify(passwordEncoder).encode("password123");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        //M.G:assert user
        User savedUser = captor.getValue();

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getId()).isExactlyInstanceOf(EntityId.class);
        assertThat(savedUser.getNickname().nickname()).isEqualTo("nickname123");
        assertThat(savedUser.getEmail().email()).isEqualTo("name@gmail.com");
        assertThat(savedUser.getPhone().phone()).isEqualTo("385957541300");
        assertThat(savedUser.getPasswordHash().password_hash()).isEqualTo("hashedpassword123");
        assertThat(savedUser.getStateOfAccount()).isEqualTo(StateOfAccount.ACTIVE);
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        assertThat(savedUser.getCreatedAt()).isBetween(timestampBefore,timestampAfter);

        //M.G: assert response
        assertThat(responseDto.id()).isEqualTo(savedUser.getId().id());



        //M.G: assert event

        ArgumentCaptor<UserSignedUpEvent> eventCaptor =
                ArgumentCaptor.forClass(UserSignedUpEvent.class);
        verify(applicationEventPublisher).publishEvent(eventCaptor.capture());

        UserSignedUpEvent event = eventCaptor.getValue();

        assertThat(event.userId()).isEqualTo(savedUser.getId().id());
        assertThat(event.signedUpAt()).isBetween(timestampBefore,timestampAfter);
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull(){
        var requestDto = new SignUpRequestDto("nickname123"
                ,"385957541300"
                ,"name@gmail.com"
                ,null);


        assertThatThrownBy(() -> signUpService.signUp(requestDto))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(userRepository, applicationEventPublisher, passwordEncoder);
    }

    @Test
    void shouldThrowExceptionWhenNicknameIsBlank(){
        var requestDto = new SignUpRequestDto(""
                ,"385957541300"
                ,"name@gmail.com"
                ,"password123");


        assertThatThrownBy(() -> signUpService.signUp(requestDto))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(userRepository, applicationEventPublisher);
    }

    @Test
    void shouldThrowExceptionWhenRepoFails(){
        var requestDto = new SignUpRequestDto("nickname123"
                ,"385957541300"
                ,"name@gmail.com"
                ,"password123");

        when(passwordEncoder.encode("password123"))
                .thenReturn("hashedPassword");

        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("db error"));

        assertThatThrownBy(() -> signUpService.signUp(requestDto))
                .isInstanceOf(RuntimeException.class);

        verifyNoInteractions(applicationEventPublisher);

    }
}
