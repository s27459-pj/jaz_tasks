package pw.karczewski.tasks.task;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pw.karczewski.tasks.validation.DictionaryConstraint;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @DictionaryConstraint("categories")
    private String category;

    @NotNull
    private Boolean done;
}
