package pw.karczewski.log;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.baeldung.openapi.model.LogCreate;
import com.baeldung.openapi.model.LogRetrieve;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LogMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Log toEntity(LogCreate logCreate);

    LogRetrieve toRetrieve(Log log);
}
