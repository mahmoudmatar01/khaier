package com.example.khaier.repository;

import com.example.khaier.entity.Post;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@NonNullApi
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findAll(Pageable pageable);
    Boolean existsByPostId(Long id);
}
