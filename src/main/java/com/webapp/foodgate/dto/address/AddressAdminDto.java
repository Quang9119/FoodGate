package com.webapp.foodgate.dto.address;

import com.webapp.foodgate.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class AddressAdminDto extends ABasicAdminDto {
    @ApiModelProperty(name="houseNumber")
    private String houseNumber;
    @ApiModelProperty(name="street")
    private String street;
    @ApiModelProperty(name="district")
    private String district;
    @ApiModelProperty(name="city")
    private String city;
    @ApiModelProperty(name="country")
    private String country;

}
