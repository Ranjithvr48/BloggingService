package com.ranjithblogs.blogging_platform.service;

import com.ranjithblogs.blogging_platform.model.Comment;

import com.ranjithblogs.blogging_platform.repos.CommentRepository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepo;

    public String addComment(Comment comment) {
        comment.setDateCreated(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment has been added!!!";
    }

    public Comment findComment(Long commentId) {
        return  commentRepo.findById(commentId).orElse(null);
    }

    public void removeComment(Comment comment) {
        commentRepo.delete(comment);
    }

    public List<Comment> getAllCommentsForPost(Long postId,Integer PageNumber,Integer PageSize) {
        Pageable p= PageRequest.of(PageNumber,PageSize);
        Page<Comment> Comments=commentRepo.findByBlogPostPostId(postId,p);
        List<Comment>content =Comments.getContent();

        return content;

    }

}
