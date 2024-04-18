package pw.karczewski.tasks.task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pw.karczewski.tasks.task.dto.TaskCreate;
import pw.karczewski.tasks.task.dto.TaskRetrieve;
import pw.karczewski.tasks.task.dto.TaskUpdate;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskRetrieve> getAll() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskRetrieve(task.getId(), task.getName(), task.getDone()))
                .collect(Collectors.toList());
    }

    public TaskRetrieve get(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return new TaskRetrieve(task.getId(), task.getName(), task.getDone());
    }

    public TaskRetrieve create(TaskCreate data) {
        Task task = new Task();
        task.setName(data.getName());
        task.setDone(false);
        taskRepository.save(task);
        return new TaskRetrieve(task.getId(), task.getName(), task.getDone());
    }

    public TaskRetrieve update(UUID id, TaskUpdate data) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(data.getName());
        task.setDone(data.getDone());
        taskRepository.save(task);
        return new TaskRetrieve(task.getId(), task.getName(), task.getDone());
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }
}
