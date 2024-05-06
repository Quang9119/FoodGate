package com.webapp.foodgate.dto.permission;

import com.webapp.foodgate.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class PermissionAdminDto extends ABasicAdminDto {

    private String name;
    private String description;
    private String nameGroup;
}
