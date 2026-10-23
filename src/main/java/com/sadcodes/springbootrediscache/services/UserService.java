package com.sadcodes.springbootrediscache.services;

import com.sadcodes.springbootrediscache.entity.User;
import com.sadcodes.springbootrediscache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User savedUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "user",key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id: " + id + " is not present"));
    }

    @CachePut(value = "user",key = "#id")
    public User updateUser(User user, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = new User();
            existingUser.setUserName(user.getUserName());
            existingUser.setRole(user.getRole());

            User savedUser = userRepository.save(existingUser);
            return savedUser;

        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @CacheEvict(value = "user",allEntries = true)
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id: " + id + " is deleted";
    }
}
