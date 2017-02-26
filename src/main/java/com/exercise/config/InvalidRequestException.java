package com.exercise.config;

/**
 * Created by arecicalov on 2/26/2017.
 */
public class InvalidRequestException extends RuntimeException {
    private ErrorInfo errorInfo;

    public InvalidRequestException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() { return errorInfo; }
}
