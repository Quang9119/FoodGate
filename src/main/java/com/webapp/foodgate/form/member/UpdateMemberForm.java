package com.webapp.foodgate.form.member;

import com.webapp.foodgate.validation.PhoneNumKind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@ApiModel
public class UpdateMemberForm {
    @ApiModelProperty(name="id")
    @NotNull(message="id can not be null")
    private Long id;

    @ApiModelProperty(name = "phoneNumber")
    @PhoneNumKind(message = "phoneNumber invalid")
    private String phoneNumber;
    @ApiModelProperty(name = "isFemale")
    private Integer isFemale;
    @ApiModelProperty(name = "email")
    @Email(message = "email is invalid")
    @NotBlank(message = "email is not blank")
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
    @NotBlank(message = "login is not blank")
    @NotNull(message = "login is not null")
    private String login;
    //    @ApiModelProperty(name = "password")
//    @NotBlank(message = "password is not blank")
//    @NotNull(message = "password is not null")
//    private String password;
    @ApiModelProperty(name = "group_id")
    private Long group_id;
    @ApiModelProperty(name = "kind")
    private int kind;
    @ApiModelProperty(name = "status")
    private int status;

}
