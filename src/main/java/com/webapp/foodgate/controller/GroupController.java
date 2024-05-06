package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.address.AddressAdminDto;
import com.webapp.foodgate.dto.group.GroupAdminDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Address;
import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.entities.criteria.GroupCriteria;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.group.RequestAddGroupForm;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.form.group.RequestPermissionForGroupFrom;
import com.webapp.foodgate.mapper.GroupMapper;
import com.webapp.foodgate.repository.GroupRepository;
import com.webapp.foodgate.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group")
@Slf4j
public class GroupController {
    @Autowired
    private final PermissionRepository permissionRepository;
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private GroupMapper groupMapper;

    public GroupController(GroupRepository groupRepository,
                           PermissionRepository permissionRepository) {
        this.groupRepository = groupRepository;
        this.permissionRepository = permissionRepository;
    }

    /**
     * @param groupCriteria
     * @param pageable
     * @return
     */
    @GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_LIST_GROUP + "')")
    public ApiMessageDto<ResponseListDto<List<GroupAdminDto>>> list(GroupCriteria groupCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<GroupAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Group> groupPage = groupRepository.findAll(groupCriteria.getSpecification(), pageable);
        ResponseListDto<List<GroupAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(groupMapper.fromEntityToListGroupAdminDto(groupPage.getContent()));
        responseListObj.setTotalPages(groupPage.getTotalPages());
        responseListObj.setTotalElements(groupPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list group success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.GET_GROUP + "')")
    public ApiMessageDto<GroupAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<GroupAdminDto> apiMessageDto = new ApiMessageDto<>();
        Group group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            throw new NotFoundException("Not found group",ErrorCode.GROUP_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(groupMapper.fromEntityToGroupAdminDto(group));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("get group success");
        return apiMessageDto;
    }


    /**
     * add new group
     *
     * @param requestAddGroupForm
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.CREATE_GROUP + "')")
    public ApiResponse<String> create(@Valid @RequestBody RequestAddGroupForm requestAddGroupForm, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Group group = new Group();
        if (groupRepository.existsByKind(requestAddGroupForm.getKind())) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.GROUP_ERROR_EXIST);
            apiMessageDto.setMessage("Group is existed");
            return apiMessageDto;
        }
        group.setName(requestAddGroupForm.getName());
        group.setKind(requestAddGroupForm.getKind());
        group.setDescription(requestAddGroupForm.getDescription());
        group.setPermissions(null);
        groupRepository.save(group);

        apiMessageDto.setMessage("add group successs");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }

    @PutMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_GROUP + "')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Group group = groupRepository.findById(id).orElse(null);
        if (group == null) {
            throw new NotFoundException("Not found group", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
        }
        groupRepository.delete(group);
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("delete group success");
        return apiMessageDto;
    }

    @PutMapping(value = "/add_permission", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADD_PERMISSION_GROUP + "')")
    public ApiResponse<?> addAuthority(@Valid @RequestBody RequestPermissionForGroupFrom requestPermissionForGroup, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Group existGroup = new Group();
        Permission existPermission = new Permission();
        if (requestPermissionForGroup.getGroupId() != null) {
            Group group = groupRepository.findById(requestPermissionForGroup.getGroupId()).orElse(null);
            if (group == null) {
                throw new NotFoundException("Not found group", ErrorCode.GROUP_ERROR_NOT_FOUND);
            }
        }
        if (requestPermissionForGroup.getPermissionId() != null) {
            Permission permission = permissionRepository.findById(requestPermissionForGroup.getPermissionId()).orElse(null);
            if (permission == null) {
                throw new NotFoundException("Not found group", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
            }
        }
        existGroup = groupRepository.findById(requestPermissionForGroup.getGroupId()).orElse(null);
        existPermission = permissionRepository.findById(requestPermissionForGroup.getPermissionId()).orElse(null);
        existGroup.getPermissions().add(existPermission);

        groupRepository.save(existGroup);

        apiMessageDto.setMessage("add new permisison to group success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }

    @PutMapping(value = "/delete_permission", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_PERMISSION_GROUP + "')")
    public ApiResponse<?> deleteAuthority(@Valid @RequestBody RequestPermissionForGroupFrom requestPermissionForGroup, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Group existGroup = new Group();
        Permission existPermission = new Permission();
        if (requestPermissionForGroup.getGroupId() != null) {
            Group group = groupRepository.findById(requestPermissionForGroup.getGroupId()).orElse(null);
            if (group == null) {
                throw new NotFoundException("Not found group", ErrorCode.GROUP_ERROR_NOT_FOUND);
            }
        }
        if (requestPermissionForGroup.getPermissionId() != null) {
            Permission permission = permissionRepository.findById(requestPermissionForGroup.getPermissionId()).orElse(null);
            if (permission == null) {
                throw new NotFoundException("Not found group", ErrorCode.PERMISSION_ERROR_NOT_FOUND);
            }
        }
        existGroup = groupRepository.findById(requestPermissionForGroup.getGroupId()).orElse(null);
        existPermission = permissionRepository.findById(requestPermissionForGroup.getPermissionId()).orElse(null);
        existGroup.getPermissions().remove(existPermission);

        groupRepository.save(existGroup);

        apiMessageDto.setMessage("delete new permisison to group success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }
}
