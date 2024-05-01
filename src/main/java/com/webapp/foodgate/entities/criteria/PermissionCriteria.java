package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.Permission;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data

public class PermissionCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String nameGroup;
    private String pCode;

    public Specification<Permission> getSpecification() {
        return new Specification<Permission>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (!StringUtils.isEmpty(getName())) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + getName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getDescription())) {
                    predicates.add(cb.like(cb.lower(root.get("description")), "%" + getDescription().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getNameGroup())) {
                    predicates.add(cb.like(cb.lower(root.get("nameGroup")), "%" + getNameGroup().toLowerCase() + "%"));
                }
                if (getPCode() != null) {
                    predicates.add(cb.equal(root.get("pCode"), getPCode()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
