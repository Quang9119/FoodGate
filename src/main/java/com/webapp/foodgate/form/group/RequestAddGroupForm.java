package com.webapp.foodgate.form.group;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestAddGroupForm {
    private int kind;
    private String name;
    private String description;
}
