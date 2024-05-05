package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;


import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "db_foodgate_restaurant")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Restaurant extends Auditable<String> {

    @Column(name = "openHour")
    private String openHour;

    @Column(name = "closeHour")
    private String closeHour;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address local;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_promote")
    private Promote promote;

    @OneToMany
    @JoinTable(name = "db_foodgate_order_restaurant",
            joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> linesOrder;

    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinTable(name = "db_foodgate_food_restaurant",
            joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
    private List<Food> linesFood;

    @Column(name = "isOpened")
    private boolean isOpened;

}
