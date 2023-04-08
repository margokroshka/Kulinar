package com.tms.kulinar.domain;


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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "count")
    private int count;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_name")
    private String product_name;

}
