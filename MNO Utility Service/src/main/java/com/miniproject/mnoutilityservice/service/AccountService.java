package com.miniproject.mnoutilityservice.service;

import com.miniproject.mnoutilityservice.entity.Account;

import java.util.Optional;

public interface AccountService {
    void updateAccount(Account account);

    Account readAccount(long userId);

    Optional<Account> getAccountByContact(String contact);


    Account createAccount (Account account);
}

