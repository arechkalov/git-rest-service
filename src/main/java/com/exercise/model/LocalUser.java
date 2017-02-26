package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
public class LocalUser implements Serializable {
    private static final long serialVersionUID = -298477062934L;

    private final String userName;
    private final String gitHubId;
    private final List<LocalRepository> repositories;

    @JsonCreator
    public LocalUser(String userName, String gitHubId, List<LocalRepository> repositories) {
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

    public List<LocalRepository> getRepositories() {
        return repositories;
    }
}
