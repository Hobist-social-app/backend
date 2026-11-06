package hobist.backend.modules.authentication.presentation.dtos.signup;

public record SignUpRequestDto(
        String nickname
        ,String phone
        ,String email
        ,String rawPassword) {
}
