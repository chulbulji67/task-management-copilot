package com.task.task_management.exception;

public class TaskExistException extends RuntimeException{
    String alreadyExist;
    public TaskExistException(String alreadyExist) {

        super(alreadyExist);
        this.alreadyExist = alreadyExist;
    }
}
