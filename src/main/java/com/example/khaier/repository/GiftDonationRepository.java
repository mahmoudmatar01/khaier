package com.example.khaier.repository;

import com.example.khaier.entity.GiftDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftDonationRepository extends JpaRepository<GiftDonation, Long> {
    List<GiftDonation> findAllBySender_UserId(Long userId);
}
