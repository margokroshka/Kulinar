package com.tms.kulinar.security;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    UserRepositoryJPA userRepository;

    @Autowired
    public CustomUserDetailService(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUsersByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(userRepository.getRole(user.getId())).build();
    }

}
