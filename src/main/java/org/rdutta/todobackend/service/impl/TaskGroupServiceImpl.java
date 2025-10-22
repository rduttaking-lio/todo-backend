package org.rdutta.todobackend.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.rdutta.todobackend.entity.TaskGroup;
import org.rdutta.todobackend.factory.TaskGroupFactory;
import org.rdutta.todobackend.repository.TaskGroupRepository;
import org.rdutta.todobackend.service.TaskGroupService;
import org.rdutta.todobackend.utils.SuccessMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskGroupServiceImpl implements TaskGroupService {
    private TaskGroupRepository groupRepository;

    @Autowired
    public TaskGroupServiceImpl(TaskGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    @Override
    public String createNewList(String name) {
        TaskGroup taskGroup;
        if(name != null && !name.isEmpty()){
             taskGroup = TaskGroupFactory.of(name);
        }else{
            taskGroup = null;
        }

        if(taskGroup != null){
            groupRepository.save(taskGroup);
        }else{
            log.error("Task group can't be created as of now.");
            throw new RuntimeException("Task group can't be created as of now.");
        }
        return SuccessMessages.SUCCESSFULLY_GROUP_CREATED;
    }

    @Transactional
    @Override
    public String deleteList(int id) {
            groupRepository.deleteById(id);
            return SuccessMessages.SUCCESSFULLY_GROUP_DELETED;
    }

    @Override
    public List<TaskGroup> getGroupList() {
        List<TaskGroup> taskGroups = groupRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        log.info("Fetched {} task groups", taskGroups.size());
        return taskGroups;
    }
}
