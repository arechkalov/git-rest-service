package com.exercise.controller;

import com.exercise.model.LocalRepository;
import com.exercise.model.RemoteRepository;
import com.exercise.model.RemoteUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by arecicalov on 2/26/2017.
 */
public class RemoteToLocalConverterTest {

    private RepositoryConverter<LocalRepository, RemoteRepository> convertor = new RemoteToLocalConverter();

    @Test
    public void convert() throws Exception {
        RemoteUser remoteUser = new RemoteUser("userName", "gitHubId");

        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.setFullName("fullName");
        remoteRepository.setCloneUrl("cloneUrl");
        remoteRepository.setCreatedAt("2015-05-18T19:06:24Z");
        remoteRepository.setDescription("description");
        remoteRepository.setFork(false);
        remoteRepository.setRemoteUser(remoteUser);

        LocalRepository localRepository = convertor.convert(remoteRepository);

        assertThat(localRepository.getFullName(), equalTo(remoteRepository.getFullName()));
        assertThat(localRepository.getCloneUrl(), equalTo(remoteRepository.getCloneUrl()));
        assertThat(localRepository.getCreatedAt(), equalTo("2015-05-18T22:06:24"));
        assertThat(localRepository.getDescription(), equalTo(remoteRepository.getDescription()));
        assertThat(localRepository.isFork(), equalTo(remoteRepository.isFork()));
    }
}