package com.example.historical.data.services;

import com.example.historical.data.models.User;
import com.example.historical.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(int id, User user){
        User user1 = userRepository.findById(id);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        return userRepository.save(user1);
    }
}
