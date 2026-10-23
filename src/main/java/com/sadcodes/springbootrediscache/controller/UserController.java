package com.sadcodes.springbootrediscache.controller;

import com.sadcodes.springbootrediscache.entity.User;
import com.sadcodes.springbootrediscache.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/saved")
    public ResponseEntity<User> savedUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.savedUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(user,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String>deleteUser(@PathVariable("userId") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }
}
