package pw.karczewski.tasks.task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baeldung.openapi.model.TaskCreate;
import com.baeldung.openapi.model.TaskRetrieve;
import com.baeldung.openapi.model.TaskUpdate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pw.karczewski.tasks.exception.NotFoundException;
import pw.karczewski.tasks.log.LogClient;
import pw.karczewski.tasks.log.LogCreate;
import pw.karczewski.tasks.log.LogLevel;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final LogClient logClient;

    public List<TaskRetrieve> getAll() {
        logClient.createLog(new LogCreate("getAll", LogLevel.INFO));
        return taskRepository.findAll().stream()
                .map(taskMapper::toRetrieve)
                .collect(Collectors.toList());
    }

    public TaskRetrieve get(UUID id) {
        logClient.createLog(new LogCreate("get " + id.toString(), LogLevel.INFO));
        var task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        return taskMapper.toRetrieve(task);
    }

    public TaskRetrieve create(TaskCreate data) {
        logClient.createLog(new LogCreate("create " + data.getName(), LogLevel.INFO));
        var task = taskRepository.save(taskMapper.toEntity(data));
        return taskMapper.toRetrieve(task);
    }

    @Transactional
    public TaskRetrieve update(UUID id, TaskUpdate data) {
        logClient.createLog(new LogCreate("update " + id.toString(), LogLevel.INFO));
        var task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        var updated = taskMapper.toUpdate(task, data);
        return taskMapper.toRetrieve(updated);
    }

    public void delete(UUID id) {
        logClient.createLog(new LogCreate("delete " + id.toString(), LogLevel.INFO));
        taskRepository.deleteById(id);
    }
}
