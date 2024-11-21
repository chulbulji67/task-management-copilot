package com.task.task_management.exception;

public class EntityNotFoundException extends RuntimeException {
    String taskNotFound;
    public EntityNotFoundException(String taskNotFound) {
        this.taskNotFound = taskNotFound;
    }
}
