package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {

}
