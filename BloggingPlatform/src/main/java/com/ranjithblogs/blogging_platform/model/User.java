package com.ranjithblogs.blogging_platform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;



@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Email
    private String userEmail;

    @NotBlank
    private String userPassword;



}
