package com.exercise.controller;

import com.exercise.config.InvalidRequestException;
import com.exercise.model.LocalRepository;
import com.exercise.model.LocalUser;
import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arecicalov on 2/20/2017.
 */
@RestController
class UserRepoController {

    private static final String GET_LOCAL_REPOSITORIES_BY_OWNER_URL = "repositories/{owner}";
    private static final String GET_REMOTE_REPOS_URL_BY_OWNER = "https://api.github.com/users/{owner}/repos";
    private static final String GET_REMOTE_USER_URL = "https://api.github.com/users/{owner}";

    private final RepositoryConverter<LocalRepository, RemoteRepository> converter;
    private final RestTemplate restTemplate;

    UserRepoController(RepositoryConverter<LocalRepository, RemoteRepository> converter, RestTemplate restTemplate) {
        this.converter = converter;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = GET_LOCAL_REPOSITORIES_BY_OWNER_URL, method = RequestMethod.GET)
    @ResponseBody
    public LocalUser get(@PathVariable("owner") String owner,
                         @RequestParam(value = "forks", required = false, defaultValue = "false") boolean forks) throws IOException {
        try {

            RemoteRepository[] remoteRepositories = restTemplate.getForEntity(GET_REMOTE_REPOS_URL_BY_OWNER, RemoteRepository[].class, owner).getBody();
        } catch (HttpClientErrorException e) {
            throw new InvalidRequestException(e.  )
        }

        RemoteUser remoteUser = restTemplate.getForObject(GET_REMOTE_USER_URL, RemoteUser.class, owner);

        List<LocalRepository> localRepositories = converter.convertAll(Arrays.asList(remoteRepositories));
        List<LocalRepository> filteredRepos = localRepositories.stream().filter(r -> r.isFork() == forks).collect(Collectors.toList());

        return new LocalUser(remoteUser.getLogin(), remoteUser.getId(), filteredRepos);
    }
}
