package com.webapp.foodgate.form.consumer;

import com.webapp.foodgate.validation.PhoneNumKind;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel
public class UpdateConsumerForm {
    @ApiModelProperty(name = "phoneNumber")
    @PhoneNumKind(message = "phoneNumber invalid")
    private String phoneNumber;
    @ApiModelProperty(name = "isFemale")
    private boolean isFemale;
    @ApiModelProperty(name = "email")
    @Email(message = "email is invalid")
    @NotBlank(message="email is not blank")
    @NotNull(message = "email is not null")
    private String email;
    @ApiModelProperty(name = "firstName")
    private String firstName;
    @ApiModelProperty(name = "lastName")
    private String lastName;
    @ApiModelProperty(name = "birthDate")
    private Date birthDate;
    @ApiModelProperty(name = "imagePath")
    private String imagePath;
    @ApiModelProperty(name = "login")
    @NotBlank(message="login is not blank")
    @NotNull(message = "login is not null")
    private String login;
    @ApiModelProperty(name="balanceWallet")
    private Long balanceWallet;

}
