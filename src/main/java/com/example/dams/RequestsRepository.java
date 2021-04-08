package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestsRepository extends JpaRepository<com.example.dams.Requests,Long> {
    @Query("SELECT u FROM Requests u WHERE u.requestID = ?1")
    com.example.dams.Requests findByRequestID(String username);
}
