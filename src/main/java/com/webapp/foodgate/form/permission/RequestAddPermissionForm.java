package com.webapp.foodgate.form.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class RequestAddPermissionForm {
    @ApiModelProperty("name")
    @NotNull(message="name can not be null")
    private String name;
    @ApiModelProperty("description")
    @NotNull(message="description can not be null")
    private String description;
    @ApiModelProperty("permissionCode")
    @NotNull(message="permissionCode can not be null")
    private String permissionCode;
    @ApiModelProperty("nameGroup")
    @NotNull(message="nameGroup can not be null")
    private String nameGroup;
}
