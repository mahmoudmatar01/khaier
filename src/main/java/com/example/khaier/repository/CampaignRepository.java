package com.example.khaier.repository;

import com.example.khaier.entity.DonationCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<DonationCampaign,Long> {
}
