package com.tms.kulinar.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="secure")
public class Secure {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secure_seq")
    @SequenceGenerator(name = "secure_seq", sequenceName = "secure_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "login")
    private int taste;

    @Column(name = "password")
    private int time;

    @Column(name = "user_id_fk")
    private int amount;
}
