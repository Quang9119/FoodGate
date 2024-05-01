package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import javax.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "db_foodgate_rating")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Rating extends Auditable<String> {
    @Column(name = "point")
    private int ratePoint;
    @Column(name = "content")
    private String content;
}
