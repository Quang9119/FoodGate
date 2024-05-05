package com.webapp.foodgate.mapper;

import com.webapp.foodgate.dto.address.AddressAdminDto;
import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.form.address.CreateNewAddress;
import com.webapp.foodgate.form.address.UpdateAddressForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})

public interface AddressMapper {
    /**
     * Mapping createAdressForm to Address
     */
    @Mapping(source = "houseNumber", target = "houseNumber")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "district", target = "district")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "country", target = "country")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreateAddressFormToAddress")
    Address fromCreateAddressFormToAddress(CreateNewAddress createNewAddress);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "houseNumber", target = "houseNumber")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "district", target = "district")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdDate",target = "createdDate")
    @Mapping(source = "modifiedDate",target = "modifiedDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromAddressToAddressAdminDto")
    AddressAdminDto fromAddressToAddressAdminDto(Address address);

    @IterableMapping(elementTargetType = AddressAdminDto.class, qualifiedByName = "fromAddressToAddressAdminDto")
    List<AddressAdminDto> fromEntityListToAddressAdminDtoList(List<Address> addresses);

    @Mapping(source = "houseNumber", target = "houseNumber")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "district", target = "district")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "country", target = "country")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromUpdateAddressFromToEntity")
    void fromUpdateAddressFromToEntity(UpdateAddressForm updateAddressForm, @MappingTarget Address address);
}
