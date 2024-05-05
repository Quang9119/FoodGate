package com.webapp.foodgate.mapper;

import com.webapp.foodgate.dto.category.CategoryDto;
import com.webapp.foodgate.entities.Category;
import com.webapp.foodgate.form.category.CreateCategoryForm;
import com.webapp.foodgate.form.category.UpdateCategoryForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})
public interface CategoryMapper {
    @Mapping(source = "name", target = "name")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateCategoryFormToCategory")
    Category fromCreateCategoryFormToCategory(CreateCategoryForm createCategoryForm);

    @IterableMapping(elementTargetType = Category.class, qualifiedByName = "fromCategoryToCategoryDto")
    List<Category> fromEntityListToCategoryList(List<Category> categories);
    @Mapping(source = "name", target = "name")
    @BeanMapping(ignoreByDefault = true)
    CategoryDto fromCategoryToCategoryDto(Category category);

    @Mapping(source = "name", target = "name")

    @BeanMapping(ignoreByDefault = true)
    @Named("fromUpdateCategoryFormToCategory")
    void fromUpdateCategoryFormToCategory(UpdateCategoryForm updateCategoryForm, @MappingTarget Category category);
}
