package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    //, JpaSpecificationExecutor<Category>
}
