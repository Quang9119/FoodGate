package com.webapp.foodgate.dto.permission;

import com.webapp.foodgate.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionAdminDto extends ABasicAdminDto {
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("description")
    private String description;
    @ApiModelProperty("nameGroup")
    private String nameGroup;
}
