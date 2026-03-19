package gio.hobist.Dto.ResponseDto;

import java.sql.Timestamp;
import java.util.UUID;


public record PostResponseDto(

    UUID id,
    UUID userId,
    String message,
    String rawImage,
    Integer likeNumber,
    Timestamp createdAt
){}
