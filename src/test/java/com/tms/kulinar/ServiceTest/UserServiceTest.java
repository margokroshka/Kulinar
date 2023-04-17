package com.tms.kulinar.ServiceTest;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.repository.UsersRepository;
import com.tms.kulinar.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    @Mock
    private UsersRepository userRepository;

    private UsersService userService;


    private User user;

    private List<User> users;

    @BeforeEach
    void setUser() {
        MockitoAnnotations.openMocks(this);
        userService = new UsersService(userRepository);
        user = new User(1, "TestName", "TestLestN", "testEmail", "80(29)0000000", "testPassword", "testLogin", "ADMIN", 1);
        users = new ArrayList<>();
        users.add(user);
        userRepository.createUser(user);
    }

    @Test
    void getAllUser() {
        when(userRepository.getAllUsers()).thenReturn((ArrayList<User>) users);
        Optional<List<User>> optionalBooks = Optional.ofNullable(userService.getAllUsers());
        assertTrue(optionalBooks.isPresent());
        verify(userRepository).getAllUsers();
    }
}
