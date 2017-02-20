package com.exercise.service;

import com.exercise.model.RequestRepository;

import java.net.URL;
import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
public interface JsonParser {
    List<RequestRepository> parseJsonFromUrl(URL url);
}
