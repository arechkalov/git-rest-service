package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by arecicalov on 2/20/2017.
 */
public class ResponseRepository implements Serializable {
    private static final long serialVersionUID = -392847924792L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault());

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final String createdAt;
    private final boolean isFork;

    @JsonCreator
    public ResponseRepository(String fullName, String description, String cloneUrl, String createdAt, boolean isFork) {

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(createdAt);
        String formattedDateTime = zonedDateTime.format(formatter);
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.createdAt = formattedDateTime;
        this.isFork = isFork;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isFork() {
        return isFork;
    }
}
