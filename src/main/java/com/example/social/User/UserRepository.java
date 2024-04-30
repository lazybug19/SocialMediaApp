package com.example.social.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @SuppressWarnings("null")
    Optional<UserModel> findById(Integer userID);

    Optional<UserModel> findByEmail(String emailId);
}
