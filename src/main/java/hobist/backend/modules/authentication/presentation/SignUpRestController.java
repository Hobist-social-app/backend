package hobist.backend.modules.authentication.presentation;

import hobist.backend.modules.authentication.application.commands.RegistrationService;
import hobist.backend.modules.users.application.commands.signUp.SignUpService;
import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpRestController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
        // TODO: refactor for call from this module
       var response= registrationService.singUp(dto);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }
}
