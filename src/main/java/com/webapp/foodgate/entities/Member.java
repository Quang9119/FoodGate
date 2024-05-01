package com.webapp.foodgate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "db_foodgate_member")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Member extends Auditable<String> {

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "isFemale")
    private int isFemale;  // 1 Female , 2 Male
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthDate")
    private Date birthDate;
    @Column(name = "imagePath", unique = true)
    private String imagePath;
    @JoinColumn(name = "login", unique = true)
    private String login;
    @JsonIgnore
    private String hashPassword;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "db_foodgate_member_addresses",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private List<Address> locals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @JoinColumn(name = "kind") // ADMIN: 1, MANAGER: 2, CONSUMER: 3
    private int kind;
}
