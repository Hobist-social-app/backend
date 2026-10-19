package hobist.backend.users.presentation.rest;

import hobist.backend.users.application.auth.SignUpService;
import hobist.backend.users.application.dtos.SignUpRequestDto;
import hobist.backend.users.application.dtos.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthenticationRestController {

    private final SignUpService signUpService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto dto) {
       var response=signUpService.signUp(dto);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }
}
