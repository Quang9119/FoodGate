package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.entities.Food;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class FoodCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;

    private String imagePath;

    private String description;

    private double price;

    private Long categoryId;

    private Integer status;

    public Specification<Food> getSpecification() {
        return new Specification<Food>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Food> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (!StringUtils.isEmpty(getTitle())) {
                    predicates.add(cb.like(cb.lower(root.get("title")), "%" + getTitle().toLowerCase() + "%"));
                }
                if (getImagePath() != null) {
                    predicates.add(cb.equal(root.get("imagePath"), getImagePath()));
                }
                if (!StringUtils.isEmpty(getDescription())) {
                    predicates.add(cb.like(cb.lower(root.get("description")), "%" + getDescription().toLowerCase() + "%"));
                }
                double price = getPrice();
                if (price != 0) {
                    predicates.add(cb.equal(root.get("price"), price));
                }
                if (getCategoryId() != null) {
                    Join<Food, Category> join = root.join("category", JoinType.INNER);
                    predicates.add(cb.equal(root.get("id"), getCategoryId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
