package com.task.task_management.exception;

public class LabelNotFoundException extends RuntimeException{
    String msg;
    public LabelNotFoundException(String msg){
        this.msg = msg;
    }
}
