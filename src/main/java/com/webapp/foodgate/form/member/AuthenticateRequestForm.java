package com.webapp.foodgate.form.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AuthenticateRequestForm {
    @ApiModelProperty(name="login")
    @NotNull(message = "login can not be null")
    private String login;
    @ApiModelProperty(name="login")
    @NotNull(message = "login can not be null")
    private String password;
    @ApiModelProperty(name="rememberMe")
    private Boolean rememberMe;

}
