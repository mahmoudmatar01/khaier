package com.example.khaier.entity.banner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "banner")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;
    private String title;
    private String description;
    private String imageUrl;
    @OneToOne
    @JoinColumn(name = "banner_image")
    private BannerImage bannerImage;

}
