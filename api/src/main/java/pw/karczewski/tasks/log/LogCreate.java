package pw.karczewski.tasks.log;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogCreate {
    public String message;
    public LogLevel level;
}
