package com.webapp.foodgate.dto.group;

import com.webapp.foodgate.dto.ABasicAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class GroupAdminDto extends ABasicAdminDto {
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("description")
    private String description;
    @ApiModelProperty("kind")
    private int kind;
}
