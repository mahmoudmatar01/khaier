package com.example.khaier.repository;

import com.example.khaier.entity.DonationCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationCategoryRepository extends JpaRepository<DonationCategories,Long> {
    List<DonationCategories> findByCharitableOrganization_OrgId(Long charityId);
}
