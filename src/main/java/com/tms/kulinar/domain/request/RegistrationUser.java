package com.tms.kulinar.domain.request;

import lombok.Data;

@Data
public class RegistrationUser {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String login;
    private String password;
}
