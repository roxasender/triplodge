package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.UserDto;
import com.nexus.triplodge.service.IUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // private final IUserService userService;

    // @PostMapping("/register")
    // public ResponseEntity<String> register(@RequestBody UserDto user) {
    //     try {
    //         userService.register(user);
    //         return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful!");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    //     }
    // }
    
}
