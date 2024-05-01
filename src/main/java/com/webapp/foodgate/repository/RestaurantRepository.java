package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//, JpaSpecificationExecutor<Restaurant>
}
