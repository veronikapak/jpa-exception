package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.User;
import com.jpa.jpademo.model.UserInfo;

import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);
    UserInfo update(UserInfo userInfo);
    UserInfo findByUserId(Integer userInfoId);
    UserInfo findByUser(User user);
    List<UserInfo> findAll();
    void deleteUserInfo(Integer id);
}
