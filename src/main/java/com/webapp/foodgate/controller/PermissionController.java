package com.webapp.foodgate.controller;

import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.entities.criteria.PermissionCriteria;
import com.webapp.foodgate.form.member.RequestAddPermissionForm;
import com.webapp.foodgate.mapper.PermissionMapper;
import com.webapp.foodgate.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
@Slf4j
public class PermissionController {
    @Autowired
    private final PermissionRepository permissionRepository;
    private PermissionMapper permissionMapper;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostMapping(value = "/add_permission")
    @PreAuthorize("hasAuthority('PER_C')")
    public ApiResponse<String> addGroup(@Valid @RequestBody RequestAddPermissionForm requestAddPermissionForm, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Permission permission = new Permission();
        if (permissionRepository.existsByPCode(requestAddPermissionForm.getPermissionCode())
                || permissionRepository.existsByName(requestAddPermissionForm.getName())) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.PERMISSION_ERROR_EXIST);
            apiMessageDto.setMessage("PERMISSION is existed");
            return apiMessageDto;
        }
        permission.setName(requestAddPermissionForm.getName());
        permission.setNameGroup(requestAddPermissionForm.getNameGroup());
        permission.setDescription(requestAddPermissionForm.getDescription());
        permission.setPCode(requestAddPermissionForm.getPermissionCode());
        permissionRepository.save(permission);

        apiMessageDto.setMessage("add group successs");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('PER_L')")
    public ApiMessageDto<ResponseListDto<List<PermissionAdminDto>>> list(PermissionCriteria permissionCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<PermissionAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Permission> permissionPage = permissionRepository.findAll(permissionCriteria.getSpecification(), pageable);
        ResponseListDto<List<PermissionAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(permissionMapper.fromEntityListToPermissionAdminDtoList(permissionPage.getContent()));
        responseListObj.setTotalPages(permissionPage.getTotalPages());
        responseListObj.setTotalElements(permissionPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list permission success");
        return responseListObjApiMessageDto;
    }
}
