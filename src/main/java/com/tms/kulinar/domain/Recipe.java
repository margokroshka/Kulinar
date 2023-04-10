package com.tms.kulinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
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
    private String type_meal;

    @Column(name = "products_id_fk")
    private Integer products_id_fk;

    @JsonIgnore
    @XmlTransient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
