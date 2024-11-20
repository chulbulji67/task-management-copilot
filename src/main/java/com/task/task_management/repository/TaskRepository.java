package com.task.task_management.repository;

import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);

}
