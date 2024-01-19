package com.example.khaier.repository;

import com.example.khaier.entity.BannerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BannerImageRepository extends JpaRepository<BannerImage,Long> {
    Optional<BannerImage>findByTitle(String title);
}
