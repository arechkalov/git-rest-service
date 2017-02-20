package com.exercise.config;

/**
 * Created by arecicalov on 2/20/2017.
 */
public class ErrorInfo {
    public final StringBuffer url;
    public final String ex;

    public ErrorInfo(StringBuffer url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}
