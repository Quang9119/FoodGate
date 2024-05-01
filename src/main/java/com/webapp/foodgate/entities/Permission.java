package com.webapp.foodgate.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "db_foodgate_permission")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends Auditable<String> {

    @Column(name = "name_permission", unique = true)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name = "name_group")
    private String nameGroup;
    @Column(name="pCode",unique = true)
    private String pCode;


}
