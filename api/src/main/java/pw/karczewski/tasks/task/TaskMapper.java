package pw.karczewski.tasks.task;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.baeldung.openapi.model.TaskCreate;
import com.baeldung.openapi.model.TaskRetrieve;
import com.baeldung.openapi.model.TaskUpdate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "done", constant = "false")
    Task toEntity(TaskCreate taskCreate);

    @Mapping(target = "id", ignore = true)
    Task toUpdate(@MappingTarget Task task, TaskUpdate taskUpdate);

    TaskRetrieve toRetrieve(Task task);
}
