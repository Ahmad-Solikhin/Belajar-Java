package com.gayuh.restfulapi.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;
}
