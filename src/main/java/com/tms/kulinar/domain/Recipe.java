package com.tms.kulinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "taste")
    private Integer taste;

    @Column(name = "time")
    private String time;

    @Column(name = "amount")
    private String amount;

    @Column(name = "complexity")
    private Integer complexity;

    @Column(name = "text")
    private String text;

    @Column(name = "type_meal")
    private String typeMeal;

    @Column(name = "products_id_fk")
    private Integer productsIdFk;

    @JsonIgnore
    @XmlTransient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
    private List<User> users;

    public Recipe(int id, int taste, String time, String amount, int complexity, String recipe, String main_meal, int productsIdFk, int i) {
    }
}
