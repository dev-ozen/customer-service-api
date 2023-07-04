package com.asd.customerserviceapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="customer")
@Entity
public class Customer {
    @Id
    private String id;
    private String name;
    private String addressId;
    private double salary;
}
