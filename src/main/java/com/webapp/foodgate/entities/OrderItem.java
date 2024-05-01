package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;


import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "db_foodgate_orderItem")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends Auditable<String> {

    @Column(name = "quanity")
    private int quantity;
    @Column(name = "isReceived")
    private boolean isReceived;
    @OneToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;
}
