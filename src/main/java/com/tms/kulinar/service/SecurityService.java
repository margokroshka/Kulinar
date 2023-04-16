package com.tms.kulinar.service;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registration(RegistrationUser registrationUser) {
        try {
            User user = new User();
            user.setFirstName(registrationUser.getFirstName());
            user.setLastName(registrationUser.getLastName());
            user.setEmail(registrationUser.getEmail());
            user.setPhone(registrationUser.getPhone());
            user.setLogin(registrationUser.getLogin());
            user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
            user.setRole("USER");
            User savedUser = (usersRepository.createUser(user));
            if (savedUser != null) {
                return true;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return false;
    }
}
