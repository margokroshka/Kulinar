package com.tms.kulinar.domain;


import com.tms.kulinar.annotation.IsNub;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
@Data
@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
    @SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "f_id_o_u")
    private int f_id_o_u;


    @Column(name = "name")
    private String name;


    @IsNub
    @Column(name = "content")
    private String content;
}
