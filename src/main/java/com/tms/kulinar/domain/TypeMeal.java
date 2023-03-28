package com.tms.kulinar.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="type_meal")
public class TypeMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_meal_seq")
    @SequenceGenerator(name = "type_meal_seq", sequenceName = "type_meal_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "m_recipe_fk")
    private int m_recipe_fk;

    @Column(name = "religious_type")
    private String religious_type;

    @Column(name = "attitude_to_meat")
    private String attitude_to_meat;

    @Column(name = "healthy_food")
    private String healthy_food;

    @Column(name = "main_meal")
    private String main_meal;
}
