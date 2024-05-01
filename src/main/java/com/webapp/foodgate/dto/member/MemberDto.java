package com.webapp.foodgate.dto.member;

import com.webapp.foodgate.validation.PhoneNumKind;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MemberDto {
    @ApiModelProperty(name = "phoneNumber")
    private String phoneNumber;
    @ApiModelProperty(name = "isFemale")
    private int isFemale;
    @ApiModelProperty(name = "email")
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
    private String login;

}
