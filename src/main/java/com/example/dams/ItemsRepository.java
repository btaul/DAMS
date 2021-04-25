package com.example.dams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long> {
        @Query("SELECT u FROM Items u WHERE u.iditems = ?1")
        com.example.dams.Items findByItemID(Long iditems);
}
