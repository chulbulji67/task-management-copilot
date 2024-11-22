package com.task.task_management.dto;

import com.task.task_management.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OutputTask {
    private List<Task> tasks;
    private int total;
}
