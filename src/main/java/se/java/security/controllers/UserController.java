package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Listing;
import se.java.security.models.User;
import se.java.security.repository.UserRepository;
import se.java.security.services.UserService;
import se.java.security.util.JwtUtil;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // list all user objects
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // list all users
        List<User> user = userRepository.findAll();

        // return values of all user objects
        return ResponseEntity.ok(user);
    }

    // get specific user
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificUser(@PathVariable String id) {
        // check if user id exists, or throw
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // return values of the user object
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User userDetails, @RequestHeader("Authorization") String token) {
        String username = extractUsernameFromToken(token);

        if (username == null || username.isEmpty()) {
            return ResponseEntity.status(401).body("Login expired, please log in again to update this user");
        }

        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if (existingUserOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User " + username + " was not found");
        }

        User existingUser = existingUserOptional.get();
        if (!existingUser.getId().equals(userRepository.findByUsername(username).get().getId())) {
            return ResponseEntity.status(403).body("You are not allowed to update this user");
        }

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setAge(userDetails.getAge());

        return ResponseEntity.ok(userRepository.save(existingUser));
    }

    // delete a user object

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        String username = extractUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token, please log in again");
        }

        Optional<User> userToDeleteOptional = userRepository.findByUsername(username);
        if (userToDeleteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User " + username + " was not found");
        }

        userRepository.delete(userToDeleteOptional.get());
        return ResponseEntity.noContent().build();
    }

    private String extractUsernameFromToken(String token) {
        try {
            return jwtUtil.extractUsername(token.substring(7));
        } catch (Exception e) {
            return null;
        }
    }
}