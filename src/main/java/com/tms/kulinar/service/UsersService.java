package com.tms.kulinar.service;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsersService {

    UsersRepository userRepository;

    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
       return userRepository.getUserById(id);
    }


    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepository.getAllUsers();
    }

    public User createUser(User user) {
         return userRepository.createUser(user);
    }

    public User updateUserById(User user) {
        return userRepository.updateUser(user);
    }

    public User deleteUser(User user) {
         userRepository.deleteUser(user);
        return null;
    }



}
