package com.example.social.User;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.*;

class SignupInput {
    private String email;
    private String name;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class LoginInput {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class ErrorOutput {
    @JsonProperty("Error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // User API Endpoints
    @RequestMapping("/signup")
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody SignupInput user) {
        if (userService.createUser(user.getEmail(), user.getName(), user.getPassword()) != null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.ok("Account Creation Successful");
    }

    @RequestMapping("/login")
    @PostMapping("/")
    public ResponseEntity<?> checkUser(@RequestBody LoginInput user) {
        int status = userService.checkCreds(user.getEmail(), user.getPassword());
        if (status == 200) {
            return ResponseEntity.ok("Login Successful");
        } else {
            ErrorOutput error = new ErrorOutput();
            if (status == 403) {
                error.setError("Username/Password Incorrect");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
            } else {
                error.setError("User does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        }

    }

    @RequestMapping("/user")
    @GetMapping("/") // GET
    public ResponseEntity<?> getUser(Integer userID) {
        if (userService.getUserById(userID) == null) {
            ErrorOutput error = new ErrorOutput();
            error.setError("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(userService.getUserById(userID));
    }

    @RequestMapping("/users")
    @GetMapping("/") // GET
    public ResponseEntity<List<UserOutput>> getAll() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
