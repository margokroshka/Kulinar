package com.tms.kulinar.service;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {
    private final UserRepositoryJPA userRepositoryJPA;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SecurityService(UserRepositoryJPA userRepositoryJPA, PasswordEncoder passwordEncoder) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean registration(RegistrationUser registrationUser) {
        try {
            User user = new User();
            user.setFirst_name(registrationUser.getFirst_name());
            user.setLast_name(registrationUser.getLast_name());
            user.setEmail(registrationUser.getEmail());
            user.setPhone(registrationUser.getPhone());
            user.setLogin(registrationUser.getLogin());
            user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));

            User savedUser = (userRepositoryJPA.save(user));
            userRepositoryJPA.addRole(savedUser.getId());
            if (savedUser != null) {
                return true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return false;
    }
}
