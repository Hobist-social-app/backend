package gio.hobist.RestController;

import gio.hobist.Dto.ResponseDto.PostResponseDto;
import gio.hobist.Service.HomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home-page")
public class PostRestController {

    private final HomePageService homePageService;

    @GetMapping("/posts/{userId}")
    public ResponseEntity<List<PostResponseDto>> getAllHomePagePosts(@PathVariable UUID userId){
        var response=homePageService.findAllPosts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
