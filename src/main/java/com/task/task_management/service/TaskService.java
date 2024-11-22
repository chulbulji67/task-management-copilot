package com.task.task_management.service;

import com.task.task_management.dto.OutputTask;
import com.task.task_management.dto.TaskPriorityChangeEvent;
import com.task.task_management.entity.Comment;
import com.task.task_management.entity.Label;
import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.exception.LabelNotFoundException;
import com.task.task_management.exception.TaskExistException;
import com.task.task_management.exception.TaskNotFoundException;
import com.task.task_management.repository.CommentRepository;
import com.task.task_management.repository.LabelRepository;
import com.task.task_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.task.task_management.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public TaskService(TaskRepository taskRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        if (taskRepository.existsById(task.getId())) {
            throw new TaskExistException("A task with ID " + task.getId() + " already exists.");
        }
        for(Label label: task.getLabels()){
            labelRepository.save(label);
        }
        return taskRepository.save(task);
    }

    public OutputTask getAllTasks() {
        List<Task> tasks= taskRepository.findAll();
        OutputTask outputTask = new OutputTask();
        outputTask.setTasks(tasks);
        outputTask.setTotal(tasks.size());
        return outputTask;
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

//    public Task updateTask(String id, Task updatedTask) {
//        Task task = getTaskById(id);
//        task.setTitle(updatedTask.getTitle());
//        task.setDescription(updatedTask.getDescription());
//        task.setStatus(updatedTask.getStatus());
//        task.setPriority(updatedTask.getPriority());
//        task.setAssignee(updatedTask.getAssignee());
//        task.setDueDate(updatedTask.getDueDate());
//        task.setUpdatedAt(LocalDateTime.now());
//        return taskRepository.save(task);
//    }

    public Task updateTask(String taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        TaskPriority oldPriority = task.getPriority();
        task.setPriority(updatedTask.getPriority());
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setAssignee(updatedTask.getAssignee());
        task.setDueDate(updatedTask.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);

        // Publish the event
        eventPublisher.publishEvent(new TaskPriorityChangeEvent(taskId, oldPriority, updatedTask.getPriority(), task.getAssignee()));

        return task;
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

//    public List<Task> getTasksByStatusAndPriority(TaskStatus status, TaskPriority priority) {
//        return taskRepository.findByStatusAndPriority(status, priority);
//    }

    public List<Task> getTasksByStatusAndPriority(TaskStatus status, TaskPriority priority) {
        System.out.println("Status is "+status+" "+priority);
        if (status != null && priority != null) {

            return taskRepository.findByStatusAndPriority(status, priority);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        } else if (priority != null) {
            return taskRepository.findByPriority(priority);
        } else {
            return taskRepository.findAll();
        }
    }

    public Task addLabelToTask(String taskId, String labelName) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Label label = labelRepository.findByName(labelName);
        if(label == null) label = new Label(labelName);
        task.getLabels().add(label);
        return taskRepository.save(task);
    }

    public Task removeLabelFromTask(String taskId, String labelName) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Label label = labelRepository.findByName(labelName);
        if(label == null)  throw  new LabelNotFoundException("Label not found");
        task.getLabels().remove(label);
        return taskRepository.save(task);
    }

}

