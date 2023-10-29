package com.ranjithblogs.blogging_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.OffsetDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Follow {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @OneToOne
    @JoinColumn(name = "current_user_id")
    private User currentUser;

    @OneToOne
    @JoinColumn(name = "current_user_follower_id")
    private User currentUserFollower;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;


}
