package com.ddimitko.projectwarehouse.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Supplier supplier;

    @OneToOne
    private Customer buyer;

    @OneToOne
    private User worker;

    @OneToMany
    private List<Product> products;

    private Double totalPrice;

}
