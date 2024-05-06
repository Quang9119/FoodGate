package com.webapp.foodgate.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FoodDto {
    @ApiModelProperty(name = "title_food")
    private String title;
    @ApiModelProperty(name = "imagePath")
    private String imagePath;
    @ApiModelProperty(name = "description")
    private String description;
    @ApiModelProperty(name = "price")
    private double price;
}
