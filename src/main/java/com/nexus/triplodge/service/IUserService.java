package com.nexus.triplodge.service;

import java.util.Map;
import java.util.Optional;

import com.nexus.triplodge.model.User;

public interface IUserService {
    Optional<User> getUserByUserName(String username);
    void updateUserProfile(Long userId, Map<String, Object> updates);
    // void register(UserDto user);
}
