package com.example.khaier.repository;

import com.example.khaier.entity.DonationCategoryCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationCategoryCaseRepository extends JpaRepository<DonationCategoryCase,Long> {
    List<DonationCategoryCase>findByDonationCategory_CategoryId(Long categoryId);
}
