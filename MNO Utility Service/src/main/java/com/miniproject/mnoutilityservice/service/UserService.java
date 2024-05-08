package com.miniproject.mnoutilityservice.service;

import com.miniproject.mnoutilityservice.entity.User;

public interface UserService  {
    void addUser(User user);

    User getUserById(long userId) throws Exception;

    User getUserByContact(String contact);

    void updateUser(User user) throws Exception;

    void deleteUser(long userId);
}
