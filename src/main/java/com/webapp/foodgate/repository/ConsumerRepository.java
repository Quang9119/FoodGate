package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ConsumerRepository extends JpaRepository<Consumer, Long> , JpaSpecificationExecutor<Consumer> {
}
