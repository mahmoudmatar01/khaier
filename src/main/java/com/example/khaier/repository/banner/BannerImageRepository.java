package com.example.khaier.repository.banner;

import com.example.khaier.entity.banner.BannerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BannerImageRepository extends JpaRepository<BannerImage,Long> {
    Optional<BannerImage>findByTitle(String title);
}
