package hobist.backend.modules.authentication.presentation.dtos.signUp;

public record SignUpRequestDto(
        String nickname
        ,String phone
        ,String email
        ,String rawPassword) {
}
