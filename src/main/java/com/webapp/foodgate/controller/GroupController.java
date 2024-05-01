package com.webapp.foodgate.controller;

import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.permission.PermissionAdminDto;
import com.webapp.foodgate.entities.Permission;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.form.group.RequestAddGroupForm;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.form.group.RequestPermissionForGroupFrom;
import com.webapp.foodgate.repository.GroupRepository;
import com.webapp.foodgate.repository.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public GroupController(GroupRepository groupRepository,
                           PermissionRepository permissionRepository) {
        this.groupRepository = groupRepository;
        this.permissionRepository = permissionRepository;
    }

    /**
     * add new group
     *
     * @param requestAddGroupForm
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/add_group")
    @PreAuthorize("hasAuthority('GRO_ADD')")
    public ApiResponse<String> addGroup(@Valid @RequestBody RequestAddGroupForm requestAddGroupForm, BindingResult bindingResult) {
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

    @PutMapping(value = "/add_permission")
//    @PreAuthorize("hasAuthority('GRO_ADD_PER')")
    public ApiResponse<?> addAuthority(@Valid @RequestBody RequestPermissionForGroupFrom requestPermissionForGroup, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Group existGroup = new Group();
        Permission existPermission = new Permission();
        if (requestPermissionForGroup.getGroupId() != null) {
            Group group = groupRepository.findById(requestPermissionForGroup.getGroupId()).orElse(null);
            if (group == null) {
                apiMessageDto.setResult(false);
                apiMessageDto.setMessage("Not found group");
                apiMessageDto.setCode(ErrorCode.GROUP_ERROR_NOT_FOUND);
            }
        }
        if (requestPermissionForGroup.getPermissionId() != null) {
            Permission permission = permissionRepository.findById(requestPermissionForGroup.getPermissionId()).orElse(null);
            if (permission == null) {
                apiMessageDto.setResult(false);
                apiMessageDto.setMessage("Not found group");
                apiMessageDto.setCode(ErrorCode.PERMISSION_ERROR_NOT_FOUND);
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

}
