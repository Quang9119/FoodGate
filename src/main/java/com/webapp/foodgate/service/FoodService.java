package com.webapp.foodgate.service;

import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.entities.Restaurant;
import com.webapp.foodgate.form.consumer.CreateFoodRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req) throws Exception;

    public Food deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFoods(Long restaurantId,
                                         String Category) throws Exception;

    public List<Food> searchFood(String keyword) throws Exception;

    public Food findFoodById(Long foodId) throws Exception;
}
