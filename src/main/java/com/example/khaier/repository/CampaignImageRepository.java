package com.example.khaier.repository;

import com.example.khaier.entity.CampaignImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignImageRepository extends JpaRepository<CampaignImage,Long> {
    Optional<CampaignImage>findByTitle(String title);
}
