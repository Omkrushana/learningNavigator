package com.crio.navigator.exception;

@SuppressWarnings("serial")
public class ExamNotFoundException extends RuntimeException {
   


    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException() {
        super();
    }

}
