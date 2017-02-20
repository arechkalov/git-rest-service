package com.exercise.controller;

import com.exercise.config.ErrorInfo;
import com.exercise.model.RequestRepository;
import com.exercise.model.ResponseRepository;
import com.exercise.model.ResponseUser;
import com.exercise.service.JsonParser;
import com.exercise.service.RepositoryService;
import com.exercise.service.UserService;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
@RestController
class UserRepoController {

    private static final String REPOSITORIES_OWNER_FORKS = "repositories/{owner}";
    private static final String GITHUB_USERS_REPOS = "https://api.github.com/users/%s/repos";

    private final UserService userService;
    private final RepositoryService repositoryService;
    private final JsonParser jsonParser;

    public UserRepoController(UserService userService, RepositoryService repositoryService, JsonParser jsonParser) {
        this.userService = userService;
        this.repositoryService = repositoryService;
        this.jsonParser = jsonParser;
    }

    @RequestMapping(value = REPOSITORIES_OWNER_FORKS, method = RequestMethod.GET)
    public ResponseUser get(@PathVariable("owner") String owner,
                            @RequestParam(value = "forks", required = false, defaultValue = "false") boolean forks) throws IOException {
        List<RequestRepository> requestRepositories = jsonParser.parseJsonFromUrl(buildUrl(owner));
        List<ResponseRepository> responseRepositories = repositoryService.get(requestRepositories, forks);

        return userService.create(owner, requestRepositories.get(0).getRequestOwner().getId(), responseRepositories);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

    private URL buildUrl(String owner) {
        try {
            return new URL(String.format(GITHUB_USERS_REPOS, owner));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ParseException(String.format(GITHUB_USERS_REPOS, owner), 0, "Could not parse URL");
        }
    }
}
