package gio.hobist.Service;

import gio.hobist.Dto.AuthenticationDto.AuthenticationRequestDto;
import gio.hobist.Dto.AuthenticationDto.AuthenticationResponseDto;
import gio.hobist.Dto.SignUp.SignUpRequestDto;
import gio.hobist.Entity.User;
import gio.hobist.Exception.AutenticationException;
import gio.hobist.Repository.UserRepository;
import gio.hobist.utils.PasswordHasher;
import gio.hobist.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto signUpUser(SignUpRequestDto DtoUser){
        if(DtoUser.name()==null || DtoUser.name().equals("") ){
            throw new AutenticationException("name missing");
        }
        else if(DtoUser.surname()==null || DtoUser.surname().equals("") ){
            throw new AutenticationException("surname missing");

        }
        else if(DtoUser.password()==null || DtoUser.password().equals("") ){
            throw new AutenticationException("password missing");
        }
        
        // Validate password strength
        var passwordErrors = PasswordValidator.validatePassword(DtoUser.password());
        if (!passwordErrors.isEmpty()) {
            throw new AutenticationException(String.join(", ", passwordErrors));
        }
        
        if(DtoUser.email()==null || DtoUser.email().equals("") ){
            throw new AutenticationException("email missing");
        }
        else if(DtoUser.confirmPassword()==null || DtoUser.confirmPassword().equals("") ){
            throw new AutenticationException("confirmed password missing");
        }
        else if(!DtoUser.password().equals(DtoUser.confirmPassword())){
            throw new AutenticationException("passwords do not match");
        }

        PasswordHasher hashingObject=new PasswordHasher();
        var hashedPassword=hashingObject.hashPassword(DtoUser.password());

        var user = new User(
                DtoUser.name(),
                DtoUser.surname(),
                DtoUser.email(),
                hashedPassword
        );

      userRepository.save(user);

      var jwt = jwtService.generateToken(user);

      return new  AuthenticationResponseDto(jwt);
    }

    public AuthenticationResponseDto logInUser(AuthenticationRequestDto DtoUser){

        if(DtoUser.email()==null || DtoUser.email().isBlank() ){
            throw new AutenticationException("email or password is missing");
        }
        else if(DtoUser.password()==null || DtoUser.password().isBlank() ){
            throw new AutenticationException("email or password is missing");
       }

        authenticationManager.authenticate(//M.G: this is connected to JwtAuthenticationFilter class
                new UsernamePasswordAuthenticationToken(
                        DtoUser.email(),
                        DtoUser.password()
                )
        );

        var user =userRepository.findByEmail(DtoUser.email());
        var jwt=jwtService.generateToken(user);

         return new AuthenticationResponseDto(jwt);


    }


}
