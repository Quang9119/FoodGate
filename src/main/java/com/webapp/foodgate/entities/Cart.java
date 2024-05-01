package com.webapp.foodgate.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "db_foodgate_cart")
public class Cart extends Auditable<String> {


    @Column(name = "total_item")
    private int totalItem;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "db_foodgate_cart_orderItem",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderItem_id", referencedColumnName = "id"))
    private List<OrderItem> lines = new ArrayList<>();
    @Column(name = "total_price")
    private Long totalPrice;
}
