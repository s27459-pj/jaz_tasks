package pw.karczewski.log;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.baeldung.openapi.model.LogLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Log {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String message;

    @NotNull
    private LogLevel level;

    @NotNull
    private OffsetDateTime timestamp = OffsetDateTime.now();
}
