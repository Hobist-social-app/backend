package gio.hobist.RestController;

import gio.hobist.Dto.AuthenticationDto.AuthenticationRequestDto;
import gio.hobist.Dto.AuthenticationDto.AuthenticationResponseDto;
import gio.hobist.Dto.SignUp.SignUpRequestDto;
import gio.hobist.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/users")
    public ResponseEntity<AuthenticationResponseDto> newUser(@RequestBody SignUpRequestDto dtoUser){
        var response= authenticationService.signUpUser(dtoUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> loginUser(@RequestBody AuthenticationRequestDto dtoUser){
        var response=authenticationService.logInUser(dtoUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
