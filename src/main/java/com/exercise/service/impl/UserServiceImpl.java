package com.exercise.service.impl;

import com.exercise.model.ResponseRepository;
import com.exercise.model.ResponseUser;
import com.exercise.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by arecicalov on 2/20/2017.
 */
@Service
class UserServiceImpl implements UserService {

    @Override
    public ResponseUser create(String owner, String gitHubId, List<ResponseRepository> responseRepositories){
        return new ResponseUser(owner, gitHubId, responseRepositories);
    }
}
