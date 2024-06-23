package com.crio.navigator.exception;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException() {
        super();
    }
}
