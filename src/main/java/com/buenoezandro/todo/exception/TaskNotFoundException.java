package com.buenoezandro.todo.exception;

public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id) {
        super(String.format("Task with ID: %s not found!", id));
    }
}
