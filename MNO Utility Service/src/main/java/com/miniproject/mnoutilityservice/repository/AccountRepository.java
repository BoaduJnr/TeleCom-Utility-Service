package com.miniproject.mnoutilityservice.repository;

import com.miniproject.mnoutilityservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Account , Long> {

    Optional<Account> findAccountByContact(String contact);
}
