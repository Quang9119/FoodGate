package com.webapp.foodgate.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "db_foodgate_payment")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Payment extends Auditable<String> {

    @Column(name = "nameMethod")
    private String nameMethod;
}
