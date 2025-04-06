package com.oussama.TodoListApp.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message){
   super(message);
    }
}
