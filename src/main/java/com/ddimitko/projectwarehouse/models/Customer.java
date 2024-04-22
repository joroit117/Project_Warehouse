package com.ddimitko.projectwarehouse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    //Personal Identification Number (ЕГН)
    private String PIN;

}
