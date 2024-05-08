package com.miniproject.mnoutilityservice.service.impl;

import com.miniproject.mnoutilityservice.entity.Account;
import com.miniproject.mnoutilityservice.entity.User;
import com.miniproject.mnoutilityservice.exception.ResourceNotFoundException;
import com.miniproject.mnoutilityservice.exception.UserAlreadyExistException;
import com.miniproject.mnoutilityservice.repository.UserRepository;
import com.miniproject.mnoutilityservice.service.AccountService;
import com.miniproject.mnoutilityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    @Override
    public void addUser(User user) {
        User userExist = getUserByContact(user.getContact());
        if (userExist == null) {
            Account account = new Account();
            account.setContact(user.getContact());
            Account userAccount = accountService.createAccount(account);
            user.setAccount(userAccount);
            userRepository.save(user);
            return;
        }
        throw new UserAlreadyExistException("Contact "+user.getContact()+"already exist");
    }

    @Override
    public User getUserByContact(String contact) {
        return userRepository.findUserByContact(contact);
    }

    @Override
    public User getUserById(long userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user != null) {
            return user;
        }
        throw new ResourceNotFoundException("Invalid user Id " + userId);
    }


    @Override
    public void updateUser(User user) {
        userRepository.save(user);

    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);

    }
}
