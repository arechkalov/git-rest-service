package com.exercise.controller;

import com.exercise.model.LocalRepository;
import com.exercise.model.RemoteRepository;
import org.springframework.stereotype.Component;

/**
 * Created by arecicalov on 2/26/2017.
 */
@Component
public class RemoteToLocalConverter implements RepositoryConverter<LocalRepository, RemoteRepository> {
    @Override
    public LocalRepository convert(RemoteRepository source) {
        return new LocalRepository(source.getFullName(),
                source.getDescription(),
                source.getCloneUrl(),
                source.getCreatedAt(),
                source.isFork());
    }
}
