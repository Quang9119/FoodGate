package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Address;
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
public class AddressCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String houseNumber;
    private String street;
    private String district;
    private String city;
    private String country;
    private Integer status;

    public Specification<Address> getSpecification() {
        return new Specification<Address>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (!StringUtils.isEmpty(getHouseNumber())) {
                    predicates.add(cb.like(cb.lower(root.get("houseNumber")), "%" + getHouseNumber().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getStreet())) {
                    predicates.add(cb.like(cb.lower(root.get("street")), "%" + getStreet().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getDistrict())) {
                    predicates.add(cb.like(cb.lower(root.get("district")), "%" + getDistrict().toLowerCase() + "%"));
                }
                if(!StringUtils.isEmpty(getCity())){
                    predicates.add(cb.like(cb.lower(root.get("city")), "%" + getCity().toLowerCase() + "%"));
                }
                if(!StringUtils.isEmpty(getCountry())){
                    predicates.add(cb.like(cb.lower(root.get("country")), "%" + getCountry().toLowerCase() + "%"));
                }
                if (getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
    }
}
