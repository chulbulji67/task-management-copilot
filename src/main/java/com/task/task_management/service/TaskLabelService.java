package com.task.task_management.service;

import com.task.task_management.entity.Label;
import com.task.task_management.entity.Task;
import com.task.task_management.exception.EntityNotFoundException;
import com.task.task_management.repository.LabelRepository;
import com.task.task_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLabelService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LabelRepository labelRepository;

    /**
     * Retrieve tasks based on specified labels.
     */
    public List<Task> getTasksByLabels(List<String> labelNames) {
        // Fetch labels by name
        List<Label> labels = labelRepository.findByNameIn(labelNames);
        if (labels.isEmpty()) {
            throw new IllegalArgumentException("No matching labels found for the provided names.");
        }
        // Find tasks associated with the labels
        return taskRepository.findByLabelsIn(labels);
    }

    /**
     * Add or remove labels for a task.
     */
    public Task updateTaskLabels(String taskId, List<String> labelNames) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        // Fetch or create labels
        List<Label> labels = labelRepository.findByNameIn(labelNames);
        for (String labelName : labelNames) {
            if (labels.stream().noneMatch(label -> label.getName().equals(labelName))) {
                // Create a new label if it doesn't exist
                Label newLabel = new Label();
                newLabel.setName(labelName);
                labels.add(labelRepository.save(newLabel));
            }
        }

        // Assign labels to the task
        task.setLabels(labels);
        return taskRepository.save(task);
    }
}

