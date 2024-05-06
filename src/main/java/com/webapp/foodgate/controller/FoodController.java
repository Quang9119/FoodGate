package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.dto.*;
import com.webapp.foodgate.dto.FoodDto;
import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.entities.criteria.FoodCriteria;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.food.CreateFoodForm;
import com.webapp.foodgate.form.food.UpdateFoodForm;
import com.webapp.foodgate.mapper.FoodMapper;
import com.webapp.foodgate.repository.CategoryRepository;
import com.webapp.foodgate.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CREATE_FOOD + "')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateFoodForm createFoodForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Food food = new Food();
        food = foodMapper.fromCreateFoodFormToFood(createFoodForm);
        Category category = categoryRepository.findById(createFoodForm.getCategoryId()).orElse(null);
        if(category == null) {
            throw new NotFoundException("Not found category of food", ErrorCode.CATEGORY_ERROR_NOT_FOUND);
        }
        food.setCategory(category);
        foodRepository.save(food);

        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Create new food success");
        return apiMessageDto;
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_FOOD + "')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateFoodForm updateFoodForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Food food = foodRepository.findById(updateFoodForm.getId()).orElse(null);
        if (food == null) {
            throw new NotFoundException("Not found food", ErrorCode.FOOD_ERROR_NOT_FOUND);
        }

        foodMapper.fromUpdateFoodFormToFood(updateFoodForm, food);
        Category category = categoryRepository.findById(updateFoodForm.getCategoryId()).orElse(null);
        if(category == null) {
            throw new NotFoundException("Not found category of food", ErrorCode.CATEGORY_ERROR_NOT_FOUND);
        }
        food.setCategory(category);
        foodRepository.save(food);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update food success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_FOOD + "')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Food food = foodRepository.findById(id).orElse(null);
        if (food == null) {
            throw new NotFoundException("Not found food", ErrorCode.FOOD_ERROR_NOT_FOUND);
        }
        foodRepository.deleteById(id);
        apiMessageDto.setMessage("Delete food success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_FOOD + "')")
    public ApiMessageDto<ResponseListDto<List<FoodDto>>> list(FoodCriteria foodCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<FoodDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Food> foodPage = foodRepository.findAll(foodCriteria.getSpecification(), pageable);
        ResponseListDto<List<FoodDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(foodMapper.fromEntityListToFoodDtoList(foodPage.getContent()));
        responseListObj.setTotalPages(foodPage.getTotalPages());
        responseListObj.setTotalElements(foodPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list food success");
        return responseListObjApiMessageDto;
    }
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_FOOD + "')")
    public ApiMessageDto<FoodDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<FoodDto> apiMessageDto = new ApiMessageDto<>();
        Food food = foodRepository.findById(id).orElse(null);
        if (food == null) {
            throw new NotFoundException("Not found food", ErrorCode.FOOD_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(foodMapper.fromFoodToFoodDto(food));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get food succes");
        return apiMessageDto;
    }
}
