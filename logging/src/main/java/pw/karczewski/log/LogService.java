package pw.karczewski.log;

import org.springframework.stereotype.Service;

import com.baeldung.openapi.model.LogCreate;
import com.baeldung.openapi.model.LogRetrieve;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final LogMapper logMapper;

    public LogRetrieve create(LogCreate data) {
        var log = logRepository.save(logMapper.toEntity(data));
        return logMapper.toRetrieve(log);
    }
}
