package com.exercise.service.impl;

import com.exercise.model.RequestRepository;
import com.exercise.model.ResponseRepository;
import com.exercise.service.RepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by arecicalov on 2/20/2017.
 */
@Service
class RepositoryServiceImpl implements RepositoryService{
    public List<ResponseRepository> get(List<RequestRepository> inRepositories, boolean isFork) {
        Assert.notNull(inRepositories);


        Stream<ResponseRepository> outRepositoryStream = inRepositories.stream()
                .filter(t -> t.isFork() == isFork)
                .map(t -> new ResponseRepository(t.getFullName(),
                        t.getDescription(),
                        t.getCloneUrl(),
                        t.getCreatedAt(),
                        t.isFork()));

        return outRepositoryStream.collect(Collectors.toList());
    }
}
