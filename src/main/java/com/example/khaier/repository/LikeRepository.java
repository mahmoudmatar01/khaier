package com.example.khaier.repository;

import com.example.khaier.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like>findAllByPostPostId(Long postId);
    Like findByPostPostIdAndUserUserId(Long postId, Long userId);
}
