package com.cholnhial.springjwtlogin.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ApplicationUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @EqualsAndHashCode.Include
    private String username;

    @EqualsAndHashCode.Exclude
    private String password;
}
