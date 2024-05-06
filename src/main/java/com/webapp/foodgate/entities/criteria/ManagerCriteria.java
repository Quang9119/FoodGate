package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Manager;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.Restaurant;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long memberId;
    private Long restaurantId;
    private Integer status;


    public Specification<Manager> getSpecification() {
        return new Specification<Manager>() {
            @Override
            public Predicate toPredicate(Root<Manager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getRestaurantId()!=null){
                    Join<Restaurant, Manager> restaurantManagerJoin = root.join("ownerRestaurant", JoinType.INNER);
                    predicates.add(cb.equal(restaurantManagerJoin.get("id"), getRestaurantId()));
                }
                if(getMemberId()!=null){
                    Join<Member, Manager> managerJoin = root.join("member", JoinType.INNER);
                    predicates.add(cb.equal(managerJoin.get("id"), getMemberId()));
                }
                if (getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
    }

}
