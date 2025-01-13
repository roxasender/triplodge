package com.nexus.triplodge.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.UserDto;
import com.nexus.triplodge.model.Role;
import com.nexus.triplodge.model.User;
import com.nexus.triplodge.model.UserRole;
import com.nexus.triplodge.repository.RoleRepository;
import com.nexus.triplodge.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUserProfile(Long userId, Map<String, Object> updates) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        System.out.println(user);
        System.out.println(updates);

        updates.forEach((key, value) -> {
            switch (key) {
                case "fullname":
                    user.setFullname((String) value);
                    break;
                case "phoneNumber":
                    String phoneNumber = (String) value;
                    if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
                        throw new IllegalArgumentException("Invalid phone number format");
                    }
                    user.setPhoneNumber(phoneNumber);
                    break;
                case "profilePictureUrl":
                    user.setProfilePictureUrl((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        userRepository.save(user);
        
    }

    @Override
    public void registerUser(UserDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFullname(user.getFullname());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setCreatedAt(new Date());

        Role role = roleRepository.findByroleName("ROLE_USER").get(); 
        UserRole userRole = new UserRole(); 
        userRole.setRole(role); 
        userRole.setUser(newUser); 
        
        Set<UserRole> roles = new HashSet<>(); 
        roles.add(userRole); 
        newUser.setRoles(roles); 

        userRepository.save(newUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(user -> modelMapper.map(user, UserDto.class))
            .collect(Collectors.toList());
    }
}
