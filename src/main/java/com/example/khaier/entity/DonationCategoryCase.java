package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "donation_cases")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DonationCategoryCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private String caseName;
    @Column(name = "hekma")
    private String maxim;
    private String description;
    private BigDecimal requiredAmount;
    private BigDecimal paidAmount;
    private BigDecimal remainingAmount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DonationCategories donationCategory;

    @OneToMany(mappedBy = "donationCase")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CaseDonation> donations;

}