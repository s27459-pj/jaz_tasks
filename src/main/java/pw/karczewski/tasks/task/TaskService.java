package pw.karczewski.tasks.task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pw.karczewski.tasks.task.dto.TaskCreate;
import pw.karczewski.tasks.task.dto.TaskMapper;
import pw.karczewski.tasks.task.dto.TaskRetrieve;
import pw.karczewski.tasks.task.dto.TaskUpdate;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskRetrieve> getAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toRetrieve)
                .collect(Collectors.toList());
    }

    public TaskRetrieve get(UUID id) {
        var task = taskRepository.findById(id).orElseThrow();
        return taskMapper.toRetrieve(task);
    }

    public TaskRetrieve create(TaskCreate data) {
        var task = taskRepository.save(taskMapper.toEntity(data));
        return taskMapper.toRetrieve(task);
    }

    @Transactional
    public TaskRetrieve update(UUID id, TaskUpdate data) {
        var task = taskRepository.findById(id).orElseThrow();
        var updated = taskMapper.toUpdate(task, data);
        return taskMapper.toRetrieve(updated);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }
}
