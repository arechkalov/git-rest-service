package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by arecicalov on 2/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteRepository implements Serializable {
    private static final long serialVersionUID = 32989453541L;

    @JsonProperty(value = "full_name")
    private String fullName;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "clone_url")
    private String cloneUrl;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "fork")
    private boolean isFork;
    @JsonProperty(value = "owner")
    private RemoteUser remoteUser;

    public RemoteRepository() {
    }

    public RemoteRepository(String fullName, String description, String cloneUrl, String createdAt, boolean isFork, RemoteUser remoteUser) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.createdAt = createdAt;
        this.isFork = isFork;
        this.remoteUser = remoteUser;
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

    public RemoteUser getRemoteUser() {
        return remoteUser;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setFork(boolean fork) {
        isFork = fork;
    }

    public void setRemoteUser(RemoteUser remoteUser) {
        this.remoteUser = remoteUser;
    }
}
