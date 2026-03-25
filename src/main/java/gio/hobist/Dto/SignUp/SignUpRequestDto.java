package gio.hobist.Dto.SignUp;

public record SignUpRequestDto(
    String name,
    String surname,
    String email,
    String password,
    String confirmPassword
){
}
