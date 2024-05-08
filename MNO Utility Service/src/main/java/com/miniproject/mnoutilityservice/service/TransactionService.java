package com.miniproject.mnoutilityservice.service;

import com.miniproject.mnoutilityservice.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void bulkAirtimePurchase(long userId, long amount, List<String> contacts);
    void purchaseAirTime(long userId , long amount) throws Exception;
    List<Transaction> getTransactions(long userId) throws Exception;
}
