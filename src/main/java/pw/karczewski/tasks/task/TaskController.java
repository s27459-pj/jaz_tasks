package pw.karczewski.tasks.task;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pw.karczewski.tasks.task.dto.TaskCreate;
import pw.karczewski.tasks.task.dto.TaskRetrieve;
import pw.karczewski.tasks.task.dto.TaskUpdate;

@RestController()
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskRetrieve> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskRetrieve getById(@PathVariable UUID id) {
        return taskService.get(id);
    }

    @PostMapping
    public TaskRetrieve create(@RequestBody TaskCreate data) {
        return taskService.create(data);
    }

    @PutMapping("/{id}")
    public TaskRetrieve update(@PathVariable UUID id, @RequestBody TaskUpdate data) {
        return taskService.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
