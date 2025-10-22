package org.rdutta.todobackend.factory;

import org.rdutta.todobackend.entity.TaskGroup;
import org.springframework.stereotype.Component;

@Component
public class TaskGroupFactory {
    public static TaskGroup of(String name){
        return TaskGroup.builder()
                .name(name)
                .build();
    }
}
