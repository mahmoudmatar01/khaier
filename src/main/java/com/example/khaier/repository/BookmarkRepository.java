package com.example.khaier.repository;

import com.example.khaier.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    List<Bookmark>findByUser_UserId(Long userId);
    boolean existsByUser_UserIdAndPost_PostId(Long userId, Long postId);
}
