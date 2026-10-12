package hobist.backend.users.presentation.rest;

import hobist.backend.users.presentation.dtos.SignUpRequestDto;
import hobist.backend.users.presentation.dtos.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationRestController {

   /* @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto signUpRequestDto) {

    }*/
}
