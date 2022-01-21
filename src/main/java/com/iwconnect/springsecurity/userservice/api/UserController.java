package com.iwconnect.springsecurity.userservice.api;

import com.iwconnect.springsecurity.userservice.domain.User;
import com.iwconnect.springsecurity.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getUser(@PathVariable(value = "username") String username) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!username.equals(principal.toString())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"No permission's to do this!");
        }
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @GetMapping("/admin/users/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserAsAdmin(@PathVariable(value = "username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

}
