package com.exercise.service;

import com.exercise.model.ResponseRepository;
import com.exercise.model.ResponseUser;

import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
public interface UserService {
    ResponseUser create(String owner, String gitHubId, List<ResponseRepository> responseRepositories);
}
