package gio.hobist.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {

    //M.G: this class is made exclusively for render.
    //render shuts down its free server after 15 min.
    //so i made cron-job.org to send requests every 10 min to keep it alive

    @GetMapping("/check")
    public ResponseEntity<String> HealthCheck() {
       return ResponseEntity.ok().build();
    }
}
