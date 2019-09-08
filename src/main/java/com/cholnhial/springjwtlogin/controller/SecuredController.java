package com.cholnhial.springjwtlogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecuredController {

    @GetMapping
    public ResponseEntity reachSecureEndpoint() {
        return new ResponseEntity("If you are reading this it means you have authenticated successfully!",
                HttpStatus.OK);
    }
}
