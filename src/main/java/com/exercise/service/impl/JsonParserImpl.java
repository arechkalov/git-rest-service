package com.exercise.service.impl;

import com.exercise.model.RequestRepository;
import com.exercise.service.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
@Service
class JsonParserImpl implements JsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<RequestRepository> parseJsonFromUrl(URL url){

        List<RequestRepository> inRepositories;
        try {
            inRepositories = mapper.readValue(url, mapper.getTypeFactory().constructCollectionType(List.class,
                    TypeFactory.defaultInstance().constructType(RequestRepository.class)));
        } catch (IOException e) {
            LOGGER.error("Couldn't find user with specified login", e.getCause());
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Username does not exist");
        }
        return inRepositories;
    }
}
