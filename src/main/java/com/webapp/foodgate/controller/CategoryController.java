package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.dto.*;
import com.webapp.foodgate.dto.category.CategoryDto;
import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.entities.criteria.CategoryCriteria;
import com.webapp.foodgate.entities.criteria.FoodCriteria;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.category.CreateCategoryForm;
import com.webapp.foodgate.form.category.UpdateCategoryForm;
import com.webapp.foodgate.form.food.CreateFoodForm;
import com.webapp.foodgate.form.food.UpdateFoodForm;
import com.webapp.foodgate.mapper.CategoryMapper;
import com.webapp.foodgate.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CREATE_CATEGORY + "')")
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCategoryForm createCategoryForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = new Category();
        category = categoryMapper.fromCreateCategoryFormToCategory(createCategoryForm);
        categoryRepository.save(category);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Create new category success");
        return apiMessageDto;
    }
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_CATEGORY + "')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCategoryForm updateCategoryForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(updateCategoryForm.getId()).orElse(null);
        if (category == null) {
            throw new NotFoundException("Not found category", ErrorCode.CATEGORY_ERROR_NOT_FOUND);
        }
        categoryMapper.fromUpdateCategoryFormToCategory(updateCategoryForm, category);
        categoryRepository.save(category);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Update category success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_CATEGORY + "')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new NotFoundException("Not found category", ErrorCode.CATEGORY_ERROR_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        apiMessageDto.setMessage("Delete category success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_CATEGORY + "')")
    public ApiMessageDto<ResponseListDto<List<Category>>> list(CategoryCriteria categoryCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<Category>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Category> categoryPage = categoryRepository.findAll(categoryCriteria.getSpecification(), pageable);
        ResponseListDto<List<Category>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(categoryMapper.fromEntityListToCategoryList(categoryPage.getContent()));
        responseListObj.setTotalPages(categoryPage.getTotalPages());
        responseListObj.setTotalElements(categoryPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list category success");
        return responseListObjApiMessageDto;
    }
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_CATEGORY + "')")
    public ApiMessageDto<CategoryDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<CategoryDto> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new NotFoundException("Not found category", ErrorCode.CATEGORY_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(categoryMapper.fromCategoryToCategoryDto(category));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get category succes");
        return apiMessageDto;
    }
}
