package org.rdutta.todobackend.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.rdutta.todobackend.dto.TaskDto;
import org.rdutta.todobackend.entity.Task;
import org.rdutta.todobackend.entity.TaskGroup;
import org.rdutta.todobackend.repository.TaskGroupRepository;
import org.rdutta.todobackend.repository.TaskRepository;
import org.rdutta.todobackend.service.TaskService;
import org.rdutta.todobackend.utils.Mapper;
import org.rdutta.todobackend.utils.SuccessMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository repository;
    private TaskGroupRepository groupRepository;
    private Mapper mapper;
    private static final String DEFAULT_GROUP_NAME = "My Tasks";

    @Autowired
    public TaskServiceImpl(TaskRepository repository, TaskGroupRepository groupRepository, Mapper mapper) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public void create(TaskDto dto) {
       try{
           Task task = mapper.toEntity(dto);
           if (task != null) {
               task.setTaskGroup(setGroupBySelection(dto.taskGroupName()));
               repository.save(task);
           }
       }catch (Exception exception){
           log.error("Can't be saved there some issue happens, while creating a task.");
       }
    }

    @Transactional
    @Override
    public String delete(int id) {
        Task task = repository.findById(id).orElse(null);
            if(task != null) {
                repository.delete(task);
                return SuccessMessages.SUCCESSFULLY_TASK_DELETED;
            }
            log.error("Task does not exist.");
            return "Task does not exist.";
    }

    @Override
    public List<Task> getTaskList() {
        List<Task> tasks = repository.findAll();
        return tasks;
    }

    private TaskGroup setGroupBySelection(String name){
        TaskGroup taskGroup;
        if(name != null && !name.isEmpty()) {
            String cleanedName = name.trim();
            taskGroup = groupRepository.findByName(cleanedName).get();
        }else{
            taskGroup = groupRepository.findByName(DEFAULT_GROUP_NAME).get();
        }
        return taskGroup;
    }
}
