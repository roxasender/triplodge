package com.nexus.triplodge.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexus.triplodge.dto.UserDto;
import com.nexus.triplodge.model.User;
import com.nexus.triplodge.service.IUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    // Fetches the user’s profile
    @GetMapping("/profile")
    public ResponseEntity<? extends User> getUserProfileByUserName(@RequestParam String username) {
        return userService.getUserByUserName(username)
            .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update user's profile
    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestParam Long userId, @RequestBody Map<String, Object> updates) {
        try {
            userService.updateUserProfile(userId, updates);
            return ResponseEntity.status(HttpStatus.OK).body("Updated user's profile successfull");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Can not updated user's profile");
        }
    }

    // GET /api/admin/users: Fetches a list of all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
}
