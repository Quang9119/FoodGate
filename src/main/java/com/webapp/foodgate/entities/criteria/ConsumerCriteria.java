package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Cart;
import com.webapp.foodgate.entities.Consumer;

import com.webapp.foodgate.entities.WishList;
import lombok.Data;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.JoinColumn;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ConsumerCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long balanceWallet;
    private Long wishListId;
    private Long cartId;
    private Integer status;

    public Specification<Consumer> getSpecification() {
        return new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getBalanceWallet() != null) {
                    predicates.add(cb.equal(root.get("balanceWallet"), getBalanceWallet()));
                }
                if(getCartId()!=null){
                    Join<Consumer, Cart> cartJoin= root.join("cart", JoinType.INNER);
                    predicates.add(cb.equal(cartJoin.get("id"),getCartId()));
                }
                if(getWishListId()!=null){
                    Join<Consumer, WishList>wishListJoin = root.join("lovedFoods",JoinType.INNER);
                    predicates.add(cb.equal(wishListJoin.get("id"),getWishListId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
