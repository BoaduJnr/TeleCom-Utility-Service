package com.miniproject.mnoutilityservice.service.impl;

import com.miniproject.mnoutilityservice.entity.Account;
import com.miniproject.mnoutilityservice.entity.Transaction;
import com.miniproject.mnoutilityservice.entity.User;
import com.miniproject.mnoutilityservice.exception.BadRequestException;
import com.miniproject.mnoutilityservice.repository.TransactionRepository;
import com.miniproject.mnoutilityservice.service.AccountService;
import com.miniproject.mnoutilityservice.service.TransactionService;
import com.miniproject.mnoutilityservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    final private TransactionRepository transactionRepository;
    final private AccountService accountService;
    final private UserService userService;

    /**
     * @param userId
     * @param amount
     * @param contacts r
     */
    @Override
    @Transactional
    public void bulkAirtimePurchase(long userId, long amount, List<String> contacts) {
        contacts.forEach(contact -> {
            try {
                purchaseAirTime(userId, amount, contact);
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @param userId
     * @param amount
     */
    @Override
    @Transactional
    public void purchaseAirTime(long userId, long amount) throws Exception {
        User user = userService.getUserById(userId);
        long newAccountBalance = user.getAccount().getBalance() + amount;
        Transaction transaction = new Transaction();
        transaction.setBalance(newAccountBalance);
        transaction.setReceived(amount);
        transaction.setAccount(user.getAccount());
        transaction.setRecipientId(user.getUserId());
        transaction.setContact(user.getContact());
        user.getAccount().setBalance(newAccountBalance);
        accountService.updateAccount(user.getAccount());
        transactionRepository.save(transaction);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public List<Transaction> getTransactions(long userId) throws Exception {
        User user = userService.getUserById(userId);
        long accountId = user.getAccount().getAccountId();
        return transactionRepository.findTransactionsByAccount_AccountIdOrRecipientId(accountId
                , accountId);
    }

    private void purchaseAirTime(long userId, long amount, String contact) throws Exception {
        Account account = userService.getUserById(userId).getAccount();
        if (amount < 0) {
            throw new BadRequestException("Amount has to greater than 1");
        }
        long newAccountBalance = account.getBalance() - amount;
        if (newAccountBalance < 0) {
            throw new BadRequestException("You dont have enough airtime");
        }
        Transaction transaction = new Transaction();
        transaction.setBalance(newAccountBalance);
        transaction.setTransferred(amount);
        transaction.setAccount(account);
        transaction.setContact(contact);
        User recipientAccountExist = userService.getUserByContact(contact);
        if (recipientAccountExist != null) {
            if (recipientAccountExist.getUserId() == userId) {
                return;
            }
            Account recipientAccount = recipientAccountExist.getAccount();
            transaction.setRecipientId(recipientAccountExist.getUserId());
            long recipientNewAccountBalance = recipientAccount.getBalance() + amount;
            recipientAccount.setBalance(recipientNewAccountBalance);
            accountService.updateAccount(recipientAccount);
        }
        account.setBalance(newAccountBalance);
        accountService.updateAccount(account);
        transactionRepository.save(transaction);
    }
}
