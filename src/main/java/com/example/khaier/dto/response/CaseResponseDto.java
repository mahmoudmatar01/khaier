package com.example.khaier.dto.response;

import com.example.khaier.entity.CaseDonation;
import com.example.khaier.entity.DonationCategories;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CaseResponseDto(
         Long caseId,
         String caseName,
         String hekma,
         String description,
         BigDecimal requiredAmount,
         BigDecimal paidAmount,
         BigDecimal remainingAmount,
         Long categoryId
) {
}