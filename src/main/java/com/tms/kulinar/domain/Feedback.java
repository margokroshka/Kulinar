package com.tms.kulinar.domain;

import com.tms.kulinar.annotation.IsNub;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Data
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
    @SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @IsNub
    @Column(name = "content")
    private String content;

    @Column(name = "recipe_id")
    private int recipe_id;
}
