package com.example.khaier.repository;

import com.example.khaier.entity.CaseDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseDonationRepository extends JpaRepository<CaseDonation,Long> {
    List<CaseDonation>findByDonationCase_CaseId(Long caseId);
    List<CaseDonation>findByOrganization_OrgId(Long caseId);
}
