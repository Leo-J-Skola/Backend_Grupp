package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.User;
import se.java.security.repository.UserRepository;
import se.java.security.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserController userController;
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserController userController, UserService userService, UserRepository userRepository) {
        this.userController = userController;
        this.userService = userService;
        this.userRepository = userRepository;
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

    // update user details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody User userDetails) {
        // check if user id exists, or throw
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // change user details
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhonenumber(userDetails.getPhonenumber());

        // return values of the user object
        return ResponseEntity.ok(userRepository.save(existingUser));
    }

    // delete a user object
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        // check if user id exists, or throw
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        // delete the object
        userRepository.deleteById(id);

        // return no values
        return ResponseEntity.noContent().build();
    }
}