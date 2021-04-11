package com.example.dams;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public void saveDonation(Donation donation) {
        this.donationRepository.save(donation);
    }

    @Override
    public Donation getDonationById(long id) {
        Optional<Donation> optional = donationRepository.findById(id);
        Donation donation = null;
        if (optional.isPresent()) {
            donation = optional.get();
        } else {
            throw new RuntimeException(" Donation not found for id :: " + id);
        }
        return donation;
    }

    @Override
    public void deleteDonationById(long id) {
        this.donationRepository.deleteById(id);
    }

    @Override
    public Page<Donation> findPaginatedDonation(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.donationRepository.findAll(pageable);
    }
}
