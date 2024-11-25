package com.task.task_management.entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    @NotBlank(message = "Assignee is required")
    private String assignee;

    @NotBlank(message = "Assignee email is required")
    @Email(message = "Invalid email format")
    private String assigneeEmail;

    @NotBlank(message = "Creator is required")
    private String creator;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToMany
    private List<Label> labels = new ArrayList<>();


}
