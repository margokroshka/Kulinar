package com.tms.kulinar.security;

import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;

@Controller
public class SecureController {
    SecurityService securityService;

    @Autowired
    public SecureController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpResponse> registrationUser(@RequestBody RegistrationUser registrationUser) {
        return new ResponseEntity<>(securityService.registration(registrationUser)? HttpStatus.CREATED:HttpStatus.CONFLICT);
    }

}
