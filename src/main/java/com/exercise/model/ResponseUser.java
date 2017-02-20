package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
public class ResponseUser implements Serializable {
    private static final long serialVersionUID = -298477062934L;

    private final String userName;
    private final String gitHubId;
    private final List<ResponseRepository> repositories;

    @JsonCreator
    public ResponseUser(String userName, String gitHubId, List<ResponseRepository> repositories) {
        this.userName = userName;
        this.gitHubId = gitHubId;
        this.repositories = repositories;
    }

    public String getUserName() {
        return userName;
    }

    public String getGitHubId() {
        return gitHubId;
    }

    public List<ResponseRepository> getRepositories() {
        return repositories;
    }
}
