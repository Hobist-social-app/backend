package hobist.backend.modules.authentication.presentation;

import hobist.backend.modules.authentication.presentation.dtos.login.LoginRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

   /* @GetMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {

        *//*var response;*//*
        // TODO: Call the login service with dto and return its response.

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }*/
}
