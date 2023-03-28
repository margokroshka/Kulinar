package com.tms.kulinar.domain.request;

import lombok.Data;

import javax.persistence.Column;
@Data
public class RegistrationUser {
    private String first_name;
    private String last_name;
    private String email;
    private int phone;
    private String login;
    private String password;
}
