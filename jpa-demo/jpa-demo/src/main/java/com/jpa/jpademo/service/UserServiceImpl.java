package com.jpa.jpademo.service;

import com.jpa.jpademo.model.*;
import com.jpa.jpademo.repository.ErrorReportRepository;
import com.jpa.jpademo.repository.UserInfoRepository;
import com.jpa.jpademo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * Created by Gran1 on 28/02/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ErrorReportRepository errorReportRepository;

    @Autowired
    private UserService userService;

    @Transactional(propagation = REQUIRES_NEW, noRollbackFor = RuntimeException.class)
    @Override
    public void createException() throws Exception {
        Exception exception = new RuntimeException("Create error");
        errorReportRepository.createError(
                ErrorReport.builder()
        .exceptionType(exception.getClass().getTypeName())
        .message(exception.getMessage())
        .build());

        throw exception;
    }

    @Transactional(propagation = REQUIRED)
    @Override
    public UserResponse create(UserRequest userRequest) throws Exception {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);

            userService.createException();

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setFirstName(userRequest.getFirstName());
        userInfo.setLastName(userRequest.getLastName());

        userInfoRepository.save(userInfo);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Transactional
    @Override
    public UserResponse update(UserRequest userRequest, Integer userId) {
        if(userRequest==null || userId==null){
            return null;
        }
        User user = userRepository.findById(userId);
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        user.setUsername(userRequest.getUsername());
        userInfo.setUser(user);
        userInfo.setFirstName(userRequest.getFirstName());
        userInfo.setLastName(userRequest.getLastName());

        userRepository.update(user);
        userInfoRepository.update(userInfo);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Transactional
    @Override
    public List<UserResponse> getAll() {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        return userInfos.stream()
                .map(userInfo-> UserResponse.builder()
                .id(userInfo.getUser().getId())
                .username(userInfo.getUser().getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build())
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponse getByUserName(String username) {
        if (username==null){
            return null;
        }
        User user = userRepository.findByUserName(username);
        UserInfo userInfo = userInfoRepository.findByUser(user);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }

    @Transactional
    @Override
    public void deleteUser(Integer userId) {
        userInfoRepository.deleteUserInfo(userId);
        userRepository.deleteUser(userId);

    }
}
