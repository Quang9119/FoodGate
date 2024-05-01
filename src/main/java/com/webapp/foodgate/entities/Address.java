package com.webapp.foodgate.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "db_foodgate_address")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable<String> {

    @Column(name="houseNumber")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

}
