package org.rdutta.todobackend.controller;

import org.rdutta.todobackend.entity.TaskGroup;
import org.rdutta.todobackend.service.TaskGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/v1/groups")
public class TaskGroupController {
    private TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addGroup(@RequestParam("name") String name){
        return new ResponseEntity<>(taskGroupService.createNewList(name), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskGroup>> fetchTaskGroups(){
        return new ResponseEntity<>(taskGroupService.getGroupList(), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteGroup(@RequestParam("id") int id){
        return new ResponseEntity<>(taskGroupService.deleteList(id), HttpStatus.OK);
    }
}
