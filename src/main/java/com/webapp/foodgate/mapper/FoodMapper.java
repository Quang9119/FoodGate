package com.webapp.foodgate.mapper;

import com.webapp.foodgate.dto.FoodDto;
import com.webapp.foodgate.entities.Food;
import com.webapp.foodgate.form.food.CreateFoodForm;
import com.webapp.foodgate.form.food.UpdateFoodForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})
public interface FoodMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateFoodFormToFood")
    Food fromCreateFoodFormToFood(CreateFoodForm createFoodForm);

    @IterableMapping(elementTargetType = FoodDto.class, qualifiedByName = "fromFoodToFoodDto")
    List<FoodDto> fromEntityListToFoodDtoList(List<Food> foods);
    @Mapping(source = "title", target = "title")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @BeanMapping(ignoreByDefault = true)
    FoodDto fromFoodToFoodDto(Food food);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromUpdateFoodFormToFood")
    void fromUpdateFoodFormToFood(UpdateFoodForm updateFoodForm, @MappingTarget Food food);
}
