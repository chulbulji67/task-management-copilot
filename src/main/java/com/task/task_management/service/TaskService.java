package com.task.task_management.service;

import com.task.task_management.entity.Comment;
import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.repository.CommentRepository;
import com.task.task_management.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.task.task_management.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public TaskService(TaskRepository taskRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(String id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setAssignee(updatedTask.getAssignee());
        task.setDueDate(updatedTask.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Comment addComment(String taskId, Comment comment) {
        Task task = getTaskById(taskId);
        comment.setTask(task);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Task> searchTasks(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByStatusAndPriority(TaskStatus status, TaskPriority priority) {
        return taskRepository.findByStatusAndPriority(status, priority);
    }

}

