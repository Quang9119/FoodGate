package com.webapp.foodgate.mapper;

import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.Permission;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})

public interface PermissionMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "nameGroup", target = "nameGroup")
    @Mapping(source = "description",target = "description")
    @BeanMapping(ignoreByDefault = true)
    PermissionAdminDto fromPermissionToPermissionAdminDto(Permission permission);

    @IterableMapping(elementTargetType = PermissionAdminDto.class, qualifiedByName = "fromPermissionToPermissionAdminDto")
    List<PermissionAdminDto> fromEntityListToPermissionAdminDtoList(List<Permission> permissions);
}
