package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "donation_categories")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DonationCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryTitle;
    @ManyToOne
    @JoinColumn(name = "org_id")
    private CharitableOrganization charitableOrganization;

    @OneToMany(mappedBy = "donationCategory")
    private List<DonationCategoryCase> donationCases;
}
