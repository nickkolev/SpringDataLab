package com.example._11_spring_data_lab.services;

import com.example._11_spring_data_lab.models.User;
import com.example._11_spring_data_lab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> found = this.userRepository
                .findByUsername(user.getUsername());

        if (found.isEmpty()) {
            this.userRepository.save(user);
        }
    }
}
