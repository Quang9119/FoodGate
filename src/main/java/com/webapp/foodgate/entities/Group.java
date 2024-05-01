package com.webapp.foodgate.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_foodgate_group")
@Getter
@Setter
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Group extends Auditable<String> {

    @Column(name = "name_group", unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "kind")
    private int kind;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "db_foodgate_permission_group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private List<Permission> permissions = new ArrayList<>();

    public Group() {
    }
}
