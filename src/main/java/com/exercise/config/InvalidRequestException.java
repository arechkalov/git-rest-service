package com.exercise.config;

/**
 * Created by arecicalov on 2/26/2017.
 */
public class InvalidRequestException extends RuntimeException {
    private String errorInfo;

    public InvalidRequestException(String message, String errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() { return errorInfo; }
}
