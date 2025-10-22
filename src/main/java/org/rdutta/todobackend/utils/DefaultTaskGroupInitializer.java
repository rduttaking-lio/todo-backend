package org.rdutta.todobackend.utils;

import org.rdutta.todobackend.entity.TaskGroup;
import org.rdutta.todobackend.repository.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskGroupInitializer {

    private final TaskGroupRepository repo;

    @Autowired
    public DefaultTaskGroupInitializer(TaskGroupRepository repo) {
        this.repo = repo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ensureDefaultGroup() {
        repo.findByName("My Tasks")
                .orElseGet(() -> {
                    TaskGroup group = TaskGroup.builder()
                            .name("My Tasks")
                            .build();
                    return repo.save(group);
                });
        System.out.println("✅ Default task group ensured at startup.");
    }
}
