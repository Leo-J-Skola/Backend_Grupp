package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.exceptions.UnauthorizedException;
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

    // get specific user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        // check if user id exists, or throw
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // return values of the user object
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException("User is not authenticated");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User existingUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getFirstName() != null && !user.getFirstName().isBlank()) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getBio() != null) {
            existingUser.setBio(user.getBio());
        }

        if (user.getAge() != null) {
            existingUser.setAge(user.getAge());
        }

        if (user.getProfilePic() != null && !user.getProfilePic().isBlank()) {
            existingUser.setProfilePic(user.getProfilePic());
        }

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