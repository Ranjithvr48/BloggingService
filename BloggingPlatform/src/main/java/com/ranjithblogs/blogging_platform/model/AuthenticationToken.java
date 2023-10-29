package com.ranjithblogs.blogging_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationToken {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column
    private String tokenValue;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;


    public AuthenticationToken(User user)
    {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
    }

}
