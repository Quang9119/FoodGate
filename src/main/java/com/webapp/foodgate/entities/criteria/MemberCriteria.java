package com.webapp.foodgate.entities.criteria;

import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Member;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MemberCriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String phoneNumber;
    private Integer isFemale;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String imagePath;
    private Long groupId;
    private int kind;
    private Integer status;

    public Specification<Member> getSpecification() {
        return new Specification<Member>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getPhoneNumber() != null) {
                    predicates.add(cb.equal(root.get("phoneNumber"), getPhoneNumber()));
                }
                if (getIsFemale() != null) {
                    predicates.add(cb.equal(root.get("isFemale"), getIsFemale()));
                }
                if (!StringUtils.isEmpty(getEmail())) {
                    predicates.add(cb.like(cb.lower(root.get("email")), "%" + getEmail().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getFirstName())) {
                    predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + getFirstName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getLastName())) {
                    predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + getLastName().toLowerCase() + "%"));
                }
                if (getBirthDate() != null) {
                    predicates.add(cb.equal(root.get("birthDate"), getBirthDate().toString()));
                }
                if (getGroupId() != null) {
                    Join<Member, Group> join = root.join("group", JoinType.INNER);
                    predicates.add(cb.equal(root.get("id"), getGroupId()));
                }
                if (getKind() > 0) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                if (getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
