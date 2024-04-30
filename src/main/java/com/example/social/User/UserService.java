package com.example.social.User;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserOutput {
    private String name;
    private String email;
    private Integer userID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getuserID() {
        return userID;
    }

    public void setuserID(Integer userID) {
        this.userID = userID;
    }
}

// User details business logic

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

    // CRUD operations
    // Create
    public UserModel createUser(String email, String name, String password) {
        try {
            UserModel existingUser = userRepo.findByEmail(email).get();
            return existingUser;
        } catch (NoSuchElementException e) {
            String hash = PasswordEncoder.encode(password);
            UserModel user = new UserModel();
            user.setPassword(hash);
            user.setName(name);
            user.setEmail(email);
            userRepo.save(user);
            return null;
        }
    }

    public int checkCreds(String email, String password) {
        try {
            UserModel existingUser = userRepo.findByEmail(email).get();
            if (PasswordEncoder.matches(password, existingUser.getPassword())) {
                return 200;
            } else {
                return 403;
            }
        } catch (NoSuchElementException e) {
            return 404;
        }
    }

    // Read
    public List<UserOutput> getUsers() {
        List<UserModel> allUsers = userRepo.findAll();
        List<UserOutput> allUsersOutput = new ArrayList<>();
        for (UserModel user : allUsers) {
            UserOutput output = new UserOutput();
            output.setName(user.getName());
            output.setEmail(user.getEmail());
            output.setuserID(user.getuserID());
            allUsersOutput.add(output);
        }
        return allUsersOutput;
    }

    public UserOutput getUserById(Integer userID) {
        try {
            UserModel user = userRepo.findById(userID).get();
            UserOutput output = new UserOutput();
            output.setName(user.getName());
            output.setEmail(user.getEmail());
            output.setuserID(user.getuserID());
            return output;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
