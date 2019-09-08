package com.cholnhial.springjwtlogin.controller;

import com.cholnhial.springjwtlogin.model.ApplicationUser;
import com.cholnhial.springjwtlogin.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("record")
    public void signUp(@RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(this.bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        applicationUserRepository.save(applicationUser);
    }

}
