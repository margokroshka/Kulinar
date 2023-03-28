package com.tms.kulinar.service;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.JwtAuthRequest;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.repository.UserRepositoryJPA;
import com.tms.kulinar.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecurityService {
    private final UserRepositoryJPA userRepositoryJPA;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityService(UserRepositoryJPA userRepositoryJPA, PasswordEncoder passwordEncoder,  JwtProvider jwtProvider) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String getToken(JwtAuthRequest jwtAuthRequest) {
        Optional<User> user = userRepositoryJPA.findUsersByLogin(jwtAuthRequest.getLogin());
        if (user.isPresent() && passwordEncoder.matches(jwtAuthRequest.getPassword(), user.get().getPassword())) {
         return jwtProvider.createJwtToken(jwtAuthRequest.getLogin());
        }
        return "";
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
