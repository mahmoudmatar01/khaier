package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "donation_cases")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DonationCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private String caseName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DonationCategories donationCategory;

}