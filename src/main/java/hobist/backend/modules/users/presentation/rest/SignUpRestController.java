package hobist.backend.modules.users.presentation.rest;

import hobist.backend.modules.users.application.commands.signUp.SignUpService;
import hobist.backend.modules.users.presentation.rest.dtos.signUp.SignUpRequestDto;
import hobist.backend.modules.users.presentation.rest.dtos.signUp.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SignUpRestController {

    private final SignUpService signUpService;

    @PostMapping
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto) {
       var response=signUpService.signUp(dto);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }
}
