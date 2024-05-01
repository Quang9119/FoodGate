package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Table(name = "db_foodgate_order")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order extends Auditable<String> {

    @Column(name = "totalItem")
    private int totalItem;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @OneToOne
    @JoinColumn(name = "paymethod_id")
    private Payment paymentMethod;

    @JoinColumn(name = "status")
    private String orderStatus;

    @JoinColumn(name = "order_date")
    private Date orderDate;

    @JoinColumn(name="delivery_date")
    private Date deliveryDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "db_foodgate_orderItem_order", joinColumns = @JoinColumn(name = "order_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "orderItem_id", referencedColumnName = "id"))
    private List<OrderItem> lines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="consumer_id")
    private Consumer consumer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address localReceiver;
}
