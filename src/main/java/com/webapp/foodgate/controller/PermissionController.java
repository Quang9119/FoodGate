package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.entities.criteria.PermissionCriteria;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.permission.RequestAddPermissionForm;
import com.webapp.foodgate.form.permission.UpdatePermissionForm;
import com.webapp.foodgate.mapper.PermissionMapper;
import com.webapp.foodgate.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
@Slf4j
public class PermissionController {
    @Autowired
    private final PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('PER_C')")
    public ApiResponse<String> create(@Valid @RequestBody RequestAddPermissionForm requestAddPermissionForm, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Permission permission = new Permission();
        if (permissionRepository.existsByPCode(requestAddPermissionForm.getPermissionCode())
                || permissionRepository.existsByName(requestAddPermissionForm.getName())) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.PERMISSION_ERROR_EXIST);
            apiMessageDto.setMessage("PERMISSION is existed");
            return apiMessageDto;
        }
        permission = permissionMapper.fromCreatePermissionFormToPermission(requestAddPermissionForm);
        permission.setPCode(requestAddPermissionForm.getPermissionCode());
        permissionRepository.save(permission);

        apiMessageDto.setMessage("add group successs");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_PERMISSION + "')")
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

    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_PERMISSION + "')")
    public ApiMessageDto<PermissionAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<PermissionAdminDto> apiMessageDto = new ApiMessageDto<>();
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission == null) {
            throw new NotFoundException("Not found permission", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(permissionMapper.fromPermissionToPermissionAdminDto(permission));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get permission success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_PERMISSION + "')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdatePermissionForm updatePermissionForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Permission permission = permissionRepository.findById(updatePermissionForm.getId()).orElse(null);
        if (permission == null) {
            throw new NotFoundException("Not found permission", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
        }
        permissionMapper.fromUpdatePermissionFormToEntity(updatePermissionForm, permission);
        permission.setPCode(updatePermissionForm.getPermissionCode());
        permissionRepository.save(permission);
        apiMessageDto.setMessage("update permission success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }

    @DeleteMapping(value = "delete/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_PERMISSION + "')")
    public ApiMessageDto<String> update(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission == null) {
            throw new NotFoundException("Not found permission", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
        }
        permissionRepository.delete(permission);

        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("delete permission success");
        return apiMessageDto;

    }
}
