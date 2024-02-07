package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "donation_cases_donations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CaseDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private CharitableOrganization organization;

    @ManyToOne
    @JoinColumn(name = "donation_case_id")
    private DonationCategoryCase donationCase;

    @Column(name = "donation_time")
    private LocalDateTime donationTime;

    private String donationWay;

    private BigDecimal amount;
}
