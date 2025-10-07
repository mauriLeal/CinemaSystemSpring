package com.cinemasystemspring.service;


import com.cinemasystemspring.model.User;
import com.cinemasystemspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }


    public User updateUser(Long id, User userDetails){
        User existingUser = findUserById(id);

        if(userDetails.getName() != null) {
            existingUser.setName(userDetails.getName());
        }

        if(userDetails.getEmail() != null) {
            existingUser.setEmail(userDetails.getEmail());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        User user = findUserById(id);

        userRepository.delete(user);
    }

    public List<User> findAllUsers(){

        return userRepository.findAll();

    }

    public User findUserEntityById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id "+ id));
    }

    public User saveUser(User user){

        // hash senha aqui
        return userRepository.save(user);
    }


}
