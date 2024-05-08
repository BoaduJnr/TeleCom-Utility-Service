package com.miniproject.mnoutilityservice.service.impl;

import com.miniproject.mnoutilityservice.entity.Account;
import com.miniproject.mnoutilityservice.entity.User;
import com.miniproject.mnoutilityservice.exception.ResourceNotFoundException;
import com.miniproject.mnoutilityservice.repository.AccountRepository;
import com.miniproject.mnoutilityservice.repository.UserRepository;
import com.miniproject.mnoutilityservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;
    final private UserRepository userRepository;


    /**
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);

    }

    /**
     * @param userId
     * @return
     */
    @Override
    public Account readAccount(long userId) {

        User user = userRepository.findUserByUserId(userId);
        if(user != null){
        return user.getAccount();
        }
        throw  new ResourceNotFoundException("Invalid user Id " + userId);

    }



    /**
     * @param contact
     * @return
     */
    @Override
    public Optional<Account> getAccountByContact(String contact) {
        return accountRepository.findAccountByContact(contact);
    }

    /**
     * @param account
     * @return void
     */
    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
