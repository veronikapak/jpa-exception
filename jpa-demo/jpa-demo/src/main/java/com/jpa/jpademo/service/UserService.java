package com.jpa.jpademo.service;

import com.jpa.jpademo.model.ErrorReport;
import com.jpa.jpademo.model.UserRequest;
import com.jpa.jpademo.model.UserResponse;

import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
public interface UserService {
    UserResponse create(UserRequest userRequest) throws Exception;
    UserResponse update(UserRequest userRequest, Integer userId);
    List<UserResponse> getAll();
    UserResponse getByUserName(String username);
    void deleteUser(Integer userId);
    void createException() throws Exception;

}
