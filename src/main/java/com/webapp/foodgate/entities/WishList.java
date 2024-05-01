package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Table(name = "db_foodgate_wishList")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class WishList extends Auditable<String> {


    @OneToMany
    @JoinTable(name = "db_foodgate_food_wishlist",
            joinColumns = @JoinColumn(name = "wishlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
    private List<Food> lovedFood = new ArrayList<>();

}
