package com.webapp.foodgate.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDto {

    @ApiModelProperty(name = "name")
    private String name;
}
