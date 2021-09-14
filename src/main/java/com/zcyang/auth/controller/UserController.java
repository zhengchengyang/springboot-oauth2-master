package com.zcyang.auth.controller;

import com.zcyang.auth.domain.UserEntity;
import com.zcyang.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @PreAuthorize("hasRole('admin')")
    public List<UserEntity> queryUserList(){
        return userService.list();
    }
}
