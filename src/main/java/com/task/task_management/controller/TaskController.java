package com.task.task_management.controller;


import com.task.task_management.dto.OutputTask;
import com.task.task_management.entity.Comment;
import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.enums.TaskStatus;
import com.task.task_management.service.DueDateReminderService;
import com.task.task_management.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
@Slf4j
public class TaskController {


    private final TaskService taskService;

    @Autowired
    private DueDateReminderService reminderService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
        log.info("Post Method called");
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<OutputTask> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable  String id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody  Task updatedTaskPriority) {
        return ResponseEntity.ok(taskService.updateTask(id, updatedTaskPriority));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable  String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable  String id, @RequestBody  Comment comment) {
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

    //For checking that due date reminder is going on email
    @GetMapping("/task-reminder")
    public String testReminder() {
        reminderService.sendDueDateReminders();
        return "Reminder job executed";
    }
}

