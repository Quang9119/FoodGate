package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayMethodRepository extends JpaRepository<Payment, Long> {
//, JpaSpecificationExecutor<Payment>
}
