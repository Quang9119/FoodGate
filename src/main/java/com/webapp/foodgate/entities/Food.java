package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "db_foodgate_food")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Food extends Auditable<String> {

    @Column(name = "title_food")
    private String title;
    @Column(name = "imagePath")
    private String imagePath;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
}
