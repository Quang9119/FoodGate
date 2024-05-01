package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    , JpaSpecificationExecutor<Order>
}
