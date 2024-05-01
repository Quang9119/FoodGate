package com.webapp.foodgate.form.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class ChangePasswordForm {

    @ApiModelProperty(name = "email")
    @Email(message = "email is invalid")
    @NotBlank(message="login is not blank")
    @NotNull(message = "login is not null")
    private String email;

    @ApiModelProperty(name = "login")
    @NotBlank(message="login is not blank")
    @NotNull(message = "login is not null")
    private String login;
    @ApiModelProperty(name = "password")
    @NotBlank(message="password is not blank")
    @NotNull(message = "password is not null")
    private String password;
}
