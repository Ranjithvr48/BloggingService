package com.ranjithblogs.blogging_platform.repos;

import com.ranjithblogs.blogging_platform.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
