package com.task.task_management.controller;


import com.task.task_management.entity.Comment;
import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.enums.TaskStatus;
import com.task.task_management.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable String id, @RequestBody Comment comment) {
        return ResponseEntity.ok(taskService.addComment(id, comment));
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Task>> searchTasks(@RequestParam TaskStatus status) {
//        return ResponseEntity.ok(taskService.searchTasks(status));
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>>getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority) {
        return ResponseEntity.ok(taskService.getTasksByStatusAndPriority(status, priority));
    }
}

