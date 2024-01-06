package com.example.khaier.repository;

import com.example.khaier.entity.Like;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByPostAndUser(Post post, User user);
    Like findByPostAndUser(Post post, User user);
}
