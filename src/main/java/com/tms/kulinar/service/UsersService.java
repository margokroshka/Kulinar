package com.tms.kulinar.service;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersService {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UsersService(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public User getUserById(int id){
       return userRepositoryJPA.findById(id).get();
    }

    public Optional<User> getUserByLogin(String login){
       return Optional.ofNullable(userRepositoryJPA.findUsersByLogin(login));
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userRepositoryJPA.findAll();
    }

    public User createUser(User user) {
         return userRepositoryJPA.save(user);
    }

    public User updateUserById(User user) {
        return userRepositoryJPA.saveAndFlush(user);
    }

    public User deleteUserById(int id) {
         userRepositoryJPA.deleteById(id);
        return null;
    }

    public String getRole(int id) {
         return  userRepositoryJPA.getRole(id);
    }

}
