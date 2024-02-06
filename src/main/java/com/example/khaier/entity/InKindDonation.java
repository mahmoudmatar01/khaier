package com.example.khaier.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "in_kind_donations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InKindDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private CharitableOrganization organization;

    @ManyToOne
    @JoinColumn(name = "in_kind_case_id")
    private InKindCase inKindCase;

    private BigDecimal itemAmount;
    private String itemName;

    @Column(name = "donation_time")
    private LocalDateTime donationTime;

    private Float lang;
    private Float lat;
    private String addressDescription;


    public InKindDonation(User user,
                          String phone,
                          CharitableOrganization organization,
                          InKindCase inKindCase,
                          BigDecimal itemAmount,
                          String itemName,
                          Float lang,
                          Float lat,
                          String addressDescription) {
        this.user = user;
        this.phone = phone;
        this.organization = organization;
        this.inKindCase = inKindCase;
        this.itemAmount = itemAmount;
        this.itemName = itemName;
        this.donationTime = LocalDateTime.now();
        this.lang = lang;
        this.lat = lat;
        this.addressDescription = addressDescription;
    }
}
