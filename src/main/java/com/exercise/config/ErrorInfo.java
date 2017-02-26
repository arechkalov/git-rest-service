package com.exercise.config;

/**
 * Created by arecicalov on 2/20/2017.
 */
class ErrorInfo {
    private final String description;
    private final String url;

    public ErrorInfo(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
