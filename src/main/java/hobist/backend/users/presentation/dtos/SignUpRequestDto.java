package hobist.backend.users.presentation.dtos;

public record SignUpRequestDto(
        String nickname
        ,String phone
        ,String email
        ,String rawPassword) {
}
