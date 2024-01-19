package com.example.khaier.repository;

import com.example.khaier.entity.CharitableOrgImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharityImageRepository extends JpaRepository<CharitableOrgImage,Long> {
    Optional<CharitableOrgImage> findByTitle(String title);
}
