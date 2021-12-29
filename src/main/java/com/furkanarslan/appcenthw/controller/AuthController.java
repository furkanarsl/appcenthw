package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser newUser) throws ResponseStatusException {
        AppUser user = userService.getUser(newUser.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username. User already exists.");
        }
        return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);
    }
}