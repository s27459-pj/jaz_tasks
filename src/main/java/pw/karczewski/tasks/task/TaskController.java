package pw.karczewski.tasks.task;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.openapi.api.TasksApi;
import com.baeldung.openapi.model.TaskCreate;
import com.baeldung.openapi.model.TaskRetrieve;
import com.baeldung.openapi.model.TaskUpdate;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController implements TasksApi {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskRetrieve>> getTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRetrieve> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.get(id));
    }

    @PostMapping
    public ResponseEntity<TaskRetrieve> createTask(@RequestBody TaskCreate data) {
        return ResponseEntity.ok(taskService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskRetrieve> updateTask(@PathVariable UUID id, @RequestBody TaskUpdate data) {
        return ResponseEntity.ok(taskService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
