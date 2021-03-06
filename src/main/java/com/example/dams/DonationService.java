package com.example.dams;

import java.util.List;

import org.springframework.data.domain.Page;


public interface DonationService {
    List<Donation> getAllDonations();
    void saveDonation(Donation donation);
    Donation getDonationById(long id);
    void deleteDonationById(long id);
    Page<Donation> findPaginatedDonation(int pageNo, int pageSize, String sortField, String sortDirection);
}
