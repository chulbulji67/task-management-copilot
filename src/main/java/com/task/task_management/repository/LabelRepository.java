package com.task.task_management.repository;

import com.task.task_management.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, String> {
    Label findByName(String labelName);
    List<Label> findByNameIn(List<String> names);

}
