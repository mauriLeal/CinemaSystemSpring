package com.cinemasystemspring.service;


import com.cinemasystemspring.model.User;
import com.cinemasystemspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("Email já cadastrado.");
        }
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }


    public User saveUser(User user){

        // hash senha aqui
        return userRepository.save(user);
    }


}
