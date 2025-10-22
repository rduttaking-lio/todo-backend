package org.rdutta.todobackend.service;

import org.rdutta.todobackend.entity.TaskGroup;

import java.util.List;

public interface TaskGroupService {
    String createNewList(String name);
    String deleteList(int id);
    List<TaskGroup> getGroupList();
}
