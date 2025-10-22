package org.rdutta.todobackend.controller;

import org.rdutta.todobackend.dto.TaskDto;
import org.rdutta.todobackend.entity.Task;
import org.rdutta.todobackend.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> addTask(@RequestBody TaskDto dto){
        taskService.create(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> fetchTasks(){
        return new ResponseEntity<>(taskService.getTaskList(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam("id") int id){
        return new ResponseEntity<>(taskService.delete(id), HttpStatus.OK);
    }
}
