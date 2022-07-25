package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long cust_id;

    @Column(name="Name")
    private String name;
    @Column(name="City")
    private String city;
    @Column(name="Email")
    private String email;
    @Column(name="Address")
    private String address;

}