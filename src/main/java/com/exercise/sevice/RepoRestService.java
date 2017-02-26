package com.exercise.sevice;

import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;

import java.util.List;

/**
 * Created by arecicalov on 2/26/2017.
 */
public interface RepoRestService {
    List<RemoteRepository> getRepos(String owner);

    RemoteUser getUser(String owner);
}
