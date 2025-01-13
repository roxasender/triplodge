package com.nexus.triplodge.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nexus.triplodge.dto.UserDto;
import com.nexus.triplodge.model.User;

public interface IUserService {
    Optional<User> getUserByUserName(String username);
    void updateUserProfile(Long userId, Map<String, Object> updates);
    void registerUser(UserDto user);
    List<UserDto> getAllUsers();
}
