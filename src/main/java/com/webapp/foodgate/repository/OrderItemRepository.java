package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    , JpaSpecificationExecutor<OrderItem>
}
