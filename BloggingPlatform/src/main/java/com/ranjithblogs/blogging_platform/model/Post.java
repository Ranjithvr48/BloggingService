package com.ranjithblogs.blogging_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String postContent;

    @Column
    private String postLocation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postOwner;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;


}
