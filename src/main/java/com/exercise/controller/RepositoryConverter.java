package com.exercise.controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arecicalov on 2/26/2017.
 */
interface RepositoryConverter<D, S> {
    default List<D> convertAll(List<S> list){
        return list.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    D convert(S source);
}
