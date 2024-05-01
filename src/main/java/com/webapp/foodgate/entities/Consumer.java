package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "db_foodgate_consumer")
public class Consumer extends Auditable<String> {

    @OneToOne
//    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "balanceWallet")
    private Long balanceWallet;

    @OneToOne
    @JoinColumn(name = "wishlist_id")
    private WishList lovedFoods;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
