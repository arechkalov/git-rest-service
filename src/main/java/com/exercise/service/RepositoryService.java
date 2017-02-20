package com.exercise.service;

import com.exercise.model.RequestRepository;
import com.exercise.model.ResponseRepository;

import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
public interface RepositoryService {
    List<ResponseRepository> get(List<RequestRepository> inRepositories, boolean isFork);
}
