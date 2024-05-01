package com.webapp.foodgate.form.manager;


import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.validation.PhoneNumKind;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateManagerForm extends SignUpMemberForm {
    @ApiModelProperty(name="ownerRestaurantId")
    @NotNull(message = "ownerRestaurantId can not be null")
    private Long ownerRestaurantId;

}
