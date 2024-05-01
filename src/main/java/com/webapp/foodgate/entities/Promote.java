package com.webapp.foodgate.entities;//package com.webiste.foodgate.entities;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "db_foodgate_promote")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Promote extends Auditable<String> {

    @Column(name = "end_date")
    private Date endDate;


    @Column(name = "start_date")
    private Date startDate;

    @Column(name="decrease_percent")
    private int percent;
}
