package hobist.backend.modules.authentication.presentation;

import hobist.backend.modules.authentication.application.commands.LoginService;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {

        var response = loginService.login(dto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
