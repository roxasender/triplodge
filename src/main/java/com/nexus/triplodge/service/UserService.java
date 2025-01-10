package com.nexus.triplodge.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nexus.triplodge.model.User;
import com.nexus.triplodge.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;

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

    // @Override
    // public void register(UserDto user) {
    //     // if username already exist
    //     if (userRepository.existsByUsername(user.getUsername())) {
    //         throw new IllegalArgumentException("Username is already existed");
    //     }
    //     // if email already exist
    //     if (userRepository.existsByEmail(user.getEmail())) {
    //         throw new IllegalArgumentException("Email is already existed");
    //     }
    //     User newUser = new User();
    //     newUser.setUsername(user.getUsername());
    //     newUser.setEmail(user.getEmail());
    //     newUser.setFullname(user.getFullname());
    //     newUser.setPhoneNumber(user.getPhoneNumber());
    //     newUser.setPassword(passwordEncoder.encode(user.getPassword()));

    // }
}
