package com.example.khaier.repository.banner;

import com.example.khaier.entity.banner.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BannerRepository extends JpaRepository<Banner,Long> {

}
