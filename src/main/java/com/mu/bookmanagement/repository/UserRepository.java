package com.mu.bookmanagement.repository;

import com.mu.bookmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsernameAndPassword (String username, String password);

    UserEntity findByUsername (String username);
}
