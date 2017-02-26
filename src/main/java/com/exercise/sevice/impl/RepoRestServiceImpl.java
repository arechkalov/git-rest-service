package com.exercise.sevice.impl;

import com.exercise.config.InvalidRequestException;
import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import com.exercise.sevice.RepoRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by arecicalov on 2/26/2017.
 */
@Service
public class RepoRestServiceImpl implements RepoRestService {

    private static final String GET_REMOTE_REPOS_URL_BY_OWNER = "https://api.github.com/users/{owner}/repos";
    private static final String GET_REMOTE_USER_URL = "https://api.github.com/users/{owner}";

    private final RestTemplate restTemplate;

    public RepoRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RemoteRepository> getRepos(String owner) {
        RemoteRepository[] remoteRepositories = restTemplate.getForEntity(GET_REMOTE_REPOS_URL_BY_OWNER, RemoteRepository[].class, owner).getBody();
        return Arrays.asList(remoteRepositories);
    }

    @Override
    public RemoteUser getUser(String owner) {
        try {
            return restTemplate.getForObject(GET_REMOTE_USER_URL, RemoteUser.class, owner);
        } catch (HttpClientErrorException e) {
            throw new InvalidRequestException("Requested URL is not available", e.getLocalizedMessage());
        }
    }
}
