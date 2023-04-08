package com.tms.kulinar.domain.request;

import lombok.Data;

@Data
public class RegistrationUser {
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String login;
    private String password;
}
