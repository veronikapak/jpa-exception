package com.jpa.jpademo.controller;

import com.jpa.jpademo.model.UserRequest;
import com.jpa.jpademo.model.UserResponse;
import com.jpa.jpademo.service.UserService;
import com.jpa.jpademo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gran1 on 28/02/2018.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse create(@RequestBody UserRequest userRequest) throws Exception {
        return userService.create(userRequest);

    }

    @PutMapping("/update/{userId}")
    public UserResponse update(@RequestBody UserRequest userRequest,
                       @PathVariable("userId") Integer userId){
        return userService.update(userRequest, userId);

    }

    @GetMapping("/all")
    public List getAll(){
        return userService.getAll();
    }

    @GetMapping("/search")
    public Object getByUsername(@RequestParam("username") String username){
        return userService.getByUserName(username);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
    }
}
