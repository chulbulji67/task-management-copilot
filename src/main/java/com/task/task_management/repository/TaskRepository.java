package com.task.task_management.repository;

import com.task.task_management.entity.Label;
import com.task.task_management.entity.Task;
import com.task.task_management.enums.TaskPriority;
import com.task.task_management.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);

    List<Task> findTasksByDueDateBefore(LocalDateTime dueDate);

    @Query("SELECT t FROM Task t WHERE t.dueDate >= :start AND t.dueDate <= :end")
    List<Task> findTasksByDueDateBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT t FROM Task t JOIN t.labels l WHERE l IN :labels")
    List<Task> findByLabelsIn(@Param("labels") List<Label> labels);


}
