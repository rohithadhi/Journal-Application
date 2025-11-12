package com.example.journalApp.service;


import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        if(user.getRoles() == null || user.getRoles().isEmpty()){
            user.setRoles(List.of("USER"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();

    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }
}
