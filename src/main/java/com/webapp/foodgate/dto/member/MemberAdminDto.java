package com.webapp.foodgate.dto.member;

import com.webapp.foodgate.dto.ABasicAdminDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MemberAdminDto extends ABasicAdminDto {
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
//    @ApiModelProperty(name = "group_id")
//    private Long group_id;
    @ApiModelProperty(name = "login")
    private String login;
    @ApiModelProperty(name = "kind")
    private int kind;
}
