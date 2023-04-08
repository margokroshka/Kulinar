package com.tms.kulinar.controller;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.exception.CustomException;
import com.tms.kulinar.exception.ResourceNotFoundException;
import com.tms.kulinar.service.UsersService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.slf4j.Logger;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User users = usersService.getUserById(id);
        return  new ResponseEntity<>(users, users.getId()!=0?HttpStatus.OK: HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        if(usersService.getAllUsers().isEmpty()){
            throw new ResourceNotFoundException("Not found any users");
        }
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(usersService.createUser(user) != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateUserById(@RequestBody @Valid User user, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(usersService.updateUserById(user) != null ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        User userResult=usersService.deleteUserById(id);
        if (userResult != null) {
            throw new CustomException("USER_WAS_NOT_DELETED");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
