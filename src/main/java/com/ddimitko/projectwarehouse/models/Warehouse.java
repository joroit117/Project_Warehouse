package com.ddimitko.projectwarehouse.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<User> workers;
    @OneToMany
    private List<Product> products;
    @OneToMany
    private List<Transactions> transactions;

    private Double cashRegister;
}
