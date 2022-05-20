package com.example.historical.data.controllers;
import com.example.historical.data.models.User;
import com.example.historical.data.repository.UserRepository;
import com.example.historical.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //Home Page
    @GetMapping("/")
    public String welcome() {
        return "<html><body>"
                + "<h1>WELCOME</h1>"
                + "</body></html>";
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(
            @PathVariable(value = "id") int id)
    {
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public User editUser(
            @PathVariable(value="id") int id, @Valid @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(
            @PathVariable(value="id") int id)
    {
        userRepository.deleteById(id);
    }

}
