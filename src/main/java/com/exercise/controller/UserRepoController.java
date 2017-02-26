package com.exercise.controller;

import com.exercise.model.LocalRepository;
import com.exercise.model.LocalUser;
import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import com.exercise.sevice.RepoRestService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arecicalov on 2/20/2017.
 */
@RestController
class UserRepoController {

    private static final String GET_LOCAL_REPOSITORIES_BY_OWNER_URL = "repositories/{owner}";

    private final RepositoryConverter<LocalRepository, RemoteRepository> converter;
    private final RepoRestService repoRestService;

    UserRepoController(RepositoryConverter<LocalRepository, RemoteRepository> converter, RepoRestService repoRestService) {
        this.converter = converter;
        this.repoRestService = repoRestService;
    }

    @RequestMapping(value = GET_LOCAL_REPOSITORIES_BY_OWNER_URL, method = RequestMethod.GET)
    @ResponseBody
    public LocalUser get(@PathVariable("owner") String owner,
                         @RequestParam(value = "forks", required = false, defaultValue = "false") boolean forks) throws IOException {

        RemoteUser remoteUser = repoRestService.getUser(owner);

        return new LocalUser(remoteUser.getLogin()
                , remoteUser.getId()
                , getLocalRepositories(forks
                , repoRestService.getRepos(owner)));
    }

    private List<LocalRepository> getLocalRepositories(boolean forks, List<RemoteRepository> remoteRepositories) {
        List<LocalRepository> localRepositories = converter.convertAll(remoteRepositories);
        return localRepositories.stream().filter(r -> r.isFork() == forks).collect(Collectors.toList());
    }
}
