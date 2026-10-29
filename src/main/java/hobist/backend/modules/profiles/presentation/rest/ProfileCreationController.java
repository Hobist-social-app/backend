package hobist.backend.modules.profiles.presentation.rest;

import hobist.backend.modules.profiles.presentation.rest.dtos.profileCreation.ProfileCreationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileCreationController {

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ProfileCreationRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }
}
