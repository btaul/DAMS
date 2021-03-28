package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<com.example.dams.User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    com.example.dams.User findByEmail(String email);
}
