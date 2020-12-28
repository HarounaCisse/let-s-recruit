package com.maliware.let.srecruit.repository;

import com.maliware.let.srecruit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
//    @Query("Select u from users where u.username=?1")
    Optional<User> findByUsername(String userName);
}
