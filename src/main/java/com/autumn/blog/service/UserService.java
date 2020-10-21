package com.autumn.blog.service;

import com.autumn.blog.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
