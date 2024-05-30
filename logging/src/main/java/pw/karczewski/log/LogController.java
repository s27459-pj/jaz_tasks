package pw.karczewski.log;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.openapi.model.LogCreate;
import com.baeldung.openapi.model.LogRetrieve;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @PostMapping
    public ResponseEntity<LogRetrieve> createLog(@RequestBody LogCreate data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(logService.create(data));
    }
}
