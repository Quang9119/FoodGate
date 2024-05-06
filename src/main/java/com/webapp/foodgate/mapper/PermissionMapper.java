package com.webapp.foodgate.mapper;

import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.form.address.UpdateAddressForm;
import com.webapp.foodgate.form.permission.RequestAddPermissionForm;
import com.webapp.foodgate.form.permission.UpdatePermissionForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})


public interface PermissionMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "nameGroup", target = "nameGroup")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromCreatePermissionFormToPermission")
    Permission fromCreatePermissionFormToPermission(RequestAddPermissionForm requestAddPermissionForm);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "nameGroup", target = "nameGroup")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromPermissionToPermissionAdminDto")
    PermissionAdminDto fromPermissionToPermissionAdminDto(Permission permission);

    @IterableMapping(elementTargetType = PermissionAdminDto.class, qualifiedByName = "fromPermissionToPermissionAdminDto")
    List<PermissionAdminDto> fromEntityListToPermissionAdminDtoList(List<Permission> permissions);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "nameGroup", target = "nameGroup")
    @Mapping(source = "description", target = "description")
    @BeanMapping(ignoreByDefault = true)
    @Named("fromUpdatePermissionFormToEntity")
    void fromUpdatePermissionFormToEntity(UpdatePermissionForm updatePermissionForm, @MappingTarget Permission permission);

}
