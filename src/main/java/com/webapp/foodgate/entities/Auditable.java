package com.webapp.foodgate.entities;//package com.example.footgate.entities;


import com.webapp.foodgate.constant.UserBaseConstant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> extends ReuseId {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "com.webapp.foodgate.service.id.IdGenerator")
    @GeneratedValue(generator = "idGenerator")
    private Long id;

    @CreatedBy
    @Column(name = "created_by", nullable = true, updatable = true)
    private T createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = true, updatable = true)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "modified_by", nullable = true)
    private T modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;

    private int status = UserBaseConstant.STATUS_ACTIVE;
}
