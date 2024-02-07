package com.example.khaier.entity;

import com.example.khaier.enums.GiftDonationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "gift_donations")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GiftDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private GiftDonationType giftDonationType;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;
    private String receiverName;
    private String receiverPhone;
    private String message;
}
