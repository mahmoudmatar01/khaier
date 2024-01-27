package com.example.khaier.repository;

import com.example.khaier.entity.Comment;
import com.example.khaier.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findByComment_CommentId(Long commentId);
}
