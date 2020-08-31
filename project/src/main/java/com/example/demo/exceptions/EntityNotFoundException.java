package com.example.demo.exceptions;

public class EntityNotFoundException extends  RuntimeException {
    private String message;
    public EntityNotFoundException(String msg){super(msg);}

    public String getMessage(String msg){
        return msg;
    }
}
