package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "campaigns")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;
    private String campaignName;
    private String campaignAdditionalName;
    private String campaignDescription;
    private LocalDateTime campaignEndDay;
    private Long numberOfBeneficiaries;
    private double amountRequired;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private CharitableOrganization charitableOrganization;

    @OneToOne
    @JoinColumn(name = "campaign-image-id")
    private CampaignImage campaignImage;

    @ManyToMany(mappedBy = "campaigns")
    private List<User> users ;

    @OneToMany(mappedBy = "campaign")
    private List<CampaignDonation> donations ;

}
