package com.example.khaier.entity;

import com.example.khaier.utils.shared.ImageData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "campaign_image")
@AllArgsConstructor
public class CampaignImage extends ImageData {
}
