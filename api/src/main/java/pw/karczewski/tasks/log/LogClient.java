package pw.karczewski.tasks.log;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "log-service", url = "http://localhost:8081")
public interface LogClient {
    @GetMapping("/logs")
    ResponseEntity<Object> createLog(@RequestBody LogCreate data);
}
