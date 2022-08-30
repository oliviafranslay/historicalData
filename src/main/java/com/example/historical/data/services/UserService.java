package com.example.historical.data.services;

import com.example.historical.data.models.User;
import com.example.historical.data.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User user) {
        User user1 = userRepository.findById(id);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        log.info("Edit user with id {}", id);
        return userRepository.save(user1);
    }
}
