package com.iwconnect.springsecurity.userservice.service;

import com.iwconnect.springsecurity.userservice.domain.Role;
import com.iwconnect.springsecurity.userservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
