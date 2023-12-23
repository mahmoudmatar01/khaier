package com.example.khaier.repository;

import com.example.khaier.entity.DonationCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCaseRepository extends JpaRepository<DonationCase,Long> {
}
