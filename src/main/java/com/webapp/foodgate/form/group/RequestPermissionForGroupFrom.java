package com.webapp.foodgate.form.group;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class RequestPermissionForGroupFrom {
    @NotNull(message = "group not null")
    private Long groupId;
    @NotNull(message = "permission not null")
    private Long permissionId;
}
