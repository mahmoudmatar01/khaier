package com.example.khaier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "charitable_organizations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CharitableOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;
    private String orgName;
    private String description;
    private String location;
    private String facebookUrl;
    private String instagramUrl;
    private String orgPhoneNumber;
    private String orgWhatsappNumber;

    @OneToOne
    @JoinColumn(name = "Charity_image")
    private CharitableOrgImage charitableOrgImage;

    @OneToMany(mappedBy = "charitableOrganization")
    private List<DonationCategories> donationCategories;

    @OneToMany(mappedBy = "charitableOrganization")
    private List<DonationCampaign> donationCampaigns;

    @OneToMany(mappedBy = "charitableOrganization")
    private List<InKindDonation> inKindDonations;

}
