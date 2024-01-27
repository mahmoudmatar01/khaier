package com.example.khaier.repository;

import com.example.khaier.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    List<Campaign>findByCharitableOrganization_OrgId(Long charityId);
}
