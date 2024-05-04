package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f WHERE f.title LIKE %:keyword% ")
    List<Food> searchFood(@Param("keyword") String keyword);
}
