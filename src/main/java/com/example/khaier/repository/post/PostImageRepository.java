package com.example.khaier.repository.post;

import com.example.khaier.entity.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage,Long> {
    Optional<PostImage>findByTitle(String title);
}
