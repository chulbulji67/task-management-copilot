package com.task.task_management.exception;

public class TaskNotFoundException extends RuntimeException{
    private String msg;
    public  TaskNotFoundException(String msg){
        this.msg = msg;
    }
}
