package com.miniproject.mnoutilityservice.repository;

import com.miniproject.mnoutilityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(long userId);

    User findUserByContact(String contact);


}
