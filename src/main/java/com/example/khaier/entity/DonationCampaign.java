package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "donation_campaigns")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DonationCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;
    private String campaignName;
    private LocalDateTime startDay;
    private LocalDateTime endDay;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private CharitableOrganization charitableOrganization;

}
