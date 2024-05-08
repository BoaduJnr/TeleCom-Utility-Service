package com.miniproject.mnoutilityservice.repository;

import com.miniproject.mnoutilityservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByAccount_AccountIdOrRecipientId(Long account_id, long recipientId);
}
