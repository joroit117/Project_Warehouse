package com.ddimitko.projectwarehouse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String manufacturer;

    @ManyToOne
    private Supplier supplier;

    private String name;
    private int quantity;
    private LocalDate entryDate;

    //продажна цена
    private Double listPrice;
    //доставна цена
    private Double retailPrice;

    public Product(){
    }

}
