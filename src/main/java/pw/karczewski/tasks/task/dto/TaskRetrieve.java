package pw.karczewski.tasks.task.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskRetrieve {
    private UUID id;
    private String name;
    private Boolean done;
}
