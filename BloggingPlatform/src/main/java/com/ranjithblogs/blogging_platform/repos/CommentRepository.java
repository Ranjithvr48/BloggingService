package com.ranjithblogs.blogging_platform.repos;

import com.ranjithblogs.blogging_platform.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByBlogPostPostId(Long postId, Pageable pageable);
}
