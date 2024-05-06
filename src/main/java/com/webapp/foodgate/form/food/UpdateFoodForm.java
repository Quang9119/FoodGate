package com.webapp.foodgate.form.food;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateFoodForm {
    @ApiModelProperty(name = "id")
    @NotNull(message="Id food can not null")
    private Long id;
    @ApiModelProperty(name = "title_food")
    @NotNull(message="Title food can not null")
    private String title;

    @ApiModelProperty(name = "imagePath")
    @NotNull(message="ImagePath food can not null")
    private String imagePath;
    @ApiModelProperty(name = "description")
    @NotNull(message="Description food can not null")
    private String description;
    
    @ApiModelProperty(name = "price")
    @NotNull(message="Price food can not null")
    private double price;
    @ApiModelProperty(name = "category_id")
    @NotNull(message="Category of food can not null")
    private Long categoryId;
}
