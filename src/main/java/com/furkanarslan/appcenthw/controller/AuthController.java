package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.dto.AppUserDto;
import com.furkanarslan.appcenthw.mapper.UserMapper;
import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("register")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser newUser) throws ResponseStatusException {
        AppUser user = userService.getUser(newUser.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username. User already exists.");
        }
        return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<AppUserDto> userInfo(Authentication authentication) {
        AppUser user = userService.getUser(authentication.getName());
        return new ResponseEntity<>(userMapper.AppUserToUserDto(user), HttpStatus.OK);
    }
}