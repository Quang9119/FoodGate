package com.webapp.foodgate.form.consumer;

import com.webapp.foodgate.form.member.SignUpMemberForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.aspectj.bridge.IMessage;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class SignUpConsumerForm extends SignUpMemberForm{
    @ApiModelProperty(name="balanceWallet")
    @NotNull(message = "balance wallet not null")
    private Long balanceWallet;
}
