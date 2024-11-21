package com.task.task_management.dto;

import com.task.task_management.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskPriorityChangeEvent {
    private String taskId;
    private TaskPriority oldPriority;
    private TaskPriority newPriority;
    private String assignee;

    // Constructor, getters, and setters
}
