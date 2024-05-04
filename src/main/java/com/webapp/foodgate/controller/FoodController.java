package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ApiResponse<String> create(@Valid @RequestBody Food req) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Food food = new Food();

        if (req.getTitle().isEmpty()) {

            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.FOOD_ERROR_TITLE);
            apiMessageDto.setMessage("Title is empty");
            return apiMessageDto;
        }
        food.setTitle(req.getTitle());
        food.setDescription(req.getDescription());
        food.setPrice(req.getPrice());
        food.setImagePath(req.getImagePath());
        foodRepository.save(food);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Add food Success");
        return apiMessageDto;
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ApiResponse<String> update(@Valid @RequestBody Food req,
                                      @RequestParam Long foodId) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isEmpty()) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.FOOD_ERROR_NOT_FOUND);
            apiMessageDto.setMessage("Food not found");
            return apiMessageDto;
        }
        Food foodUpdate = food.get();
        foodUpdate.setTitle(req.getTitle());
        foodUpdate.setPrice(req.getPrice());
        foodUpdate.setDescription(req.getDescription());
        foodUpdate.setImagePath(req.getImagePath());
        foodRepository.save(foodUpdate);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update food Success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{foodId}")
    @PermitAll
    public ApiResponse<String> delete(@Valid @PathVariable Long foodId) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isEmpty()) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.FOOD_ERROR_NOT_FOUND);
            apiMessageDto.setMessage("Food not found");
            return apiMessageDto;
        }
        foodRepository.delete(food.get());
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Delete food Success");
        return apiMessageDto;
    }
    @GetMapping(value = "/search")
    @PermitAll
    public ResponseListDto<List<Food>> search(@RequestParam String keyword) {
        ResponseListDto<List<Food>> responseListDto = new ResponseListDto<>();
        List<Food> foods = foodRepository.searchFood(keyword);
        responseListDto.setContent(foods);
        responseListDto.setTotalElements((long) foods.size());

        return responseListDto;
    }
}
