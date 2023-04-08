package com.tms.kulinar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tms.kulinar.controller.UserController;
import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    MockMvc mvc;

    @Mock
    UsersService userService;

    @InjectMocks
    UserController userController;

    private final User user = new User();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(userController).build();
        user.setFirst_name("Test_Name");
        user.setLast_name("Test_Last-name");
        user.setEmail("test-email@gmail.com");
        user.setPhone("80(29)0000000");
        user.setLogin("Test_Login");
        user.setPassword("test_password");
    }

    @Test
    void findAllUsers() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User());
        when(userService.getAllUsers()).thenReturn(list);

        MvcResult result = mvc.perform(get("/user/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void findUserById() throws Exception {
        when(userService.getUserById(anyInt())).thenReturn(user);

        MvcResult result = mvc.perform(get("/user/{id}", anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(userService, times(1)).getUserById(anyInt());
    }

    @Test
    void createUser() throws Exception {
        when(userService.createUser(any())).thenReturn(user);
        ObjectMapper mapper = new ObjectMapper();
        //  mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new RegistrationUser());

        MvcResult result = mvc.perform(post("/user/create").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(userService, times(1)).createUser(any());
    }

    @Test
    void updateUser() throws Exception {
        when(userService.updateUserById(any(User.class))).thenReturn(user);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new User());

        MvcResult result = mvc.perform(put("/user//update/{id}").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(userService, times(1)).updateUserById(any(User.class));
    }

    @Test
    void deleteUserById() throws Exception {
        MvcResult result = mvc.perform(delete("/user/{id}", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(userService, times(1)).deleteUserById(anyInt());
    }
}
