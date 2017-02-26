package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by arecicalov on 2/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteUser implements Serializable {
    private static final long serialVersionUID = -36989454541L;

    private String login;
    private String id;

    public RemoteUser(){

    }
    public RemoteUser(String login, String id) {
        this.login = login;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }
}
