package com.ranjithblogs.blogging_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    User commenter;

    @ManyToOne
    @JoinColumn(name = "blog_post_id")
    Post blogPost;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;



}
