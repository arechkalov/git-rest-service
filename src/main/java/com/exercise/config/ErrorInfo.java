package com.exercise.config;

/**
 * Created by arecicalov on 2/20/2017.
 */
public class ErrorInfo {
    private final String url;
    private final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}
