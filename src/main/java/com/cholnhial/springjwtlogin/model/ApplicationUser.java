package com.cholnhial.springjwtlogin.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
public class ApplicationUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private String username;

    @EqualsAndHashCode.Exclude
    private String password;
}
