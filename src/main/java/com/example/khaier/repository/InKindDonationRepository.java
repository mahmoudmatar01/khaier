package com.example.khaier.repository;

import com.example.khaier.entity.InKindDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InKindDonationRepository extends JpaRepository<InKindDonation,Long> {
    List<InKindDonation>findByOrganization_OrgId(Long orgId);
}
