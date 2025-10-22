package org.rdutta.todobackend.utils;

import org.rdutta.todobackend.dto.TaskDto;
import org.rdutta.todobackend.entity.Task;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Mapper {
    public Task toEntity(TaskDto dto){
        Function<TaskDto, Task> converter = taskDto -> Task.builder()
                .title(taskDto.title())
                .description(taskDto.description())
                .build();

        return converter.apply(dto);
    }
}
