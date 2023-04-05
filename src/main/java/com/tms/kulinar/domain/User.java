package com.tms.kulinar.domain;


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_k_id_seq")
    @SequenceGenerator(name = "users_k_id_seq", sequenceName = "users_k_id_seq", allocationSize = 1)
    int id;
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    private static final Logger log = LoggerFactory.getLogger(User.class);

    public User() {
    }

}
