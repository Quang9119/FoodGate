package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface WishListRepository extends JpaRepository<WishList, Long>,JpaSpecificationExecutor<WishList>{
}
