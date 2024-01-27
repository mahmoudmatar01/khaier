package com.example.khaier.repository;

import com.example.khaier.entity.CampaignDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignDonationRepository extends JpaRepository<CampaignDonation,Long> {
    List<CampaignDonation>findByUser_UserId(Long userId);
}
