package com.webapp.foodgate.form.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class UpdateAddressForm {
    @ApiModelProperty(name = "id")
    private Long id;
    @ApiModelProperty(name = "houseNumber")
    @NotNull(message = "Phone Number can not null")
    private String houseNumber;
    @ApiModelProperty(name = "street")
    @NotNull(message = "Street can not null")
    private String street;
    @ApiModelProperty(name = "district")
    @NotNull(message = "District can not null")
    private String district;
    @ApiModelProperty(name = "city")
    @NotNull(message = "City can not null")
    private String city;
    @ApiModelProperty(name = "country")
    @NotNull(message = "Country can not null")
    private String country;
}
