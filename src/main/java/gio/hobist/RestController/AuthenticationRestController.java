package gio.hobist.RestController;

import gio.hobist.Dto.AuthenticationDto;
import gio.hobist.Dto.ResponseDto.AuthenticationSignUpResponseDto;
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
    public ResponseEntity<AuthenticationSignUpResponseDto> newUser(@RequestBody AuthenticationDto dtoUser){
        var response= authenticationService.signUpUser(dtoUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSignUpResponseDto> loginUser(@RequestBody AuthenticationDto dtoUser){
        var response=authenticationService.logInUser(dtoUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
