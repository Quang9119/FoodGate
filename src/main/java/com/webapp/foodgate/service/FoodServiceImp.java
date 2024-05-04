//package com.webapp.foodgate.service;
//
//import com.webapp.foodgate.entities.Category;
//import com.webapp.foodgate.entities.Food;
//import com.webapp.foodgate.entities.Restaurant;
//import com.webapp.foodgate.form.consumer.CreateFoodRequest;
//import com.webapp.foodgate.repository.CategoryRepository;
//import com.webapp.foodgate.repository.FoodRepository;
//import com.webapp.foodgate.repository.RestaurantRepository;
//import net.bytebuddy.implementation.bytecode.Throw;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class FoodServiceImp implements FoodService{
//    @Autowired
//    private FoodRepository foodRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    @Override
//    public Food createFood(CreateFoodRequest req) throws Exception {
//        Food food = new Food();
//        food.setDescription(req.getDescription());
//        food.setPrice(req.getPrice());
//        food.setTitle(req.getTitle());
//        food.setImagePath(req.getImagePath());
//        Restaurant  restaurant = restaurantRepository.findById(req.getRestaurantId()).get();
//        Category category = categoryRepository.findById(req.getCategoryId()).get();
//        food.setCategory(category);
//        food.setRestaurant(restaurant);
//        Food savedFood = foodRepository.save(food);
//
//        return savedFood;
//    }
//
//
//    @Override
//    public Food deleteFood(Long foodId) throws Exception {
//        Food food = findFoodById(foodId);
//        food.setStatus(0);
//        foodRepository.save(food);
//        return food;
//    }
//
//    @Override
//    public List<Food> getRestaurantFoods(Long restaurantId, String Category) throws Exception {
//        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
//        if(Category != null)
//            foods = filterByCategory(foods,Category);
//        return foods;
//    }
//
//    private List<Food> filterByCategory(List<Food> foods, String category) {
//        return foods.stream().filter(food ->{
//            if(food.getCategory().getName()!=null) {
//                return food.getCategory().getName().equals(category);
//            }
//
//            return false;
//        }).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Food> searchFood(String keyword) throws Exception {
//        return foodRepository.searchFood(keyword);
//    }
//
//    @Override
//    public Food findFoodById(Long foodId) throws Exception {
//        Optional<Food> food = foodRepository.findById(foodId);
//        if(food.isPresent())
//            return food.get();
//        else
//            throw new Exception("Food not found");
//    }
//}
