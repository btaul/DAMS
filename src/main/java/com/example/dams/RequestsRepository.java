package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RequestsRepository extends JpaRepository<com.example.dams.Requests,Long> {
    @Query("SELECT u FROM Requests u WHERE u.requestID = ?1")
    com.example.dams.Requests findByRequestID(Long username);

    @Transactional
    @Modifying
    @Query("UPDATE Requests  r set r.status = 'inactive' where r.requestID=:id")
    void eRequest(Long id);
}
