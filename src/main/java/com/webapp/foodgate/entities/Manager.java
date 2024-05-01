package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_foodgate_manager")
public class Manager extends Auditable<String> {

    @OneToOne
//    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant ownerRestaurant;
}
