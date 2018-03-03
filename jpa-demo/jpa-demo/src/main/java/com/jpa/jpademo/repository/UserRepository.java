package com.jpa.jpademo.repository;

import com.jpa.jpademo.model.User;

import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
public interface UserRepository {

    User save(User user);
    User update(User user);
    User findById(Integer userId);
    List<User> findAll();
    User findByUserName(String username);
    void deleteUser(Integer userId);
}
