package com.task.task_management.controller;

import com.task.task_management.entity.Task;
import com.task.task_management.service.TaskLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskLabelController {

    @Autowired
    private TaskLabelService taskLabelService;

    /**
     * Retrieve tasks by labels.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Task>> getTasksByLabels(@RequestParam List<String> labels) {
        List<Task> tasks = taskLabelService.getTasksByLabels(labels);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Add or remove labels for a specific task.
     */
    @PutMapping("/{id}/labels")
    public ResponseEntity<Task> updateTaskLabels(
            @PathVariable String id,
            @RequestBody List<String> labelNames) {
        Task updatedTask = taskLabelService.updateTaskLabels(id, labelNames);
        return ResponseEntity.ok(updatedTask);
    }
}

