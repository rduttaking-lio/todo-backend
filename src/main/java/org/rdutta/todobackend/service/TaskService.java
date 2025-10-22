package org.rdutta.todobackend.service;

import org.rdutta.todobackend.dto.TaskDto;
import org.rdutta.todobackend.entity.Task;

import java.util.List;

public interface TaskService {
    void create(TaskDto dto);
    String delete(int id);
    List<Task> getTaskList();
}
