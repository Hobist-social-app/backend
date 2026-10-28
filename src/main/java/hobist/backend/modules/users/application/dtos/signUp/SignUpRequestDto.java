package hobist.backend.modules.users.application.dtos.signUp;

public record SignUpRequestDto(
        String nickname
        ,String phone
        ,String email
        ,String rawPassword) {
}
