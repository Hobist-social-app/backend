package hobist.backend.modules.users.presentation.rest.dtos.signUp;

public record SignUpRequestDto(
        String nickname
        ,String phone
        ,String email
        ,String rawPassword) {
}
