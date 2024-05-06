package com.webapp.foodgate.form.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateCategoryForm {
    @ApiModelProperty(name="category_id")
    @NotNull(message="Id category can not null")
    private Long id;
    @ApiModelProperty(name="name")
    @NotNull(message="Name category can not null")
    private String name;

}
