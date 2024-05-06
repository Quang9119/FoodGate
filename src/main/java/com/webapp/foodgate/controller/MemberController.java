package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.AuthoritiesConstants;
import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.member.MemberDto;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.exception.BadRequestException;
import com.webapp.foodgate.exception.NotFoundException;
import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.form.member.UpdateMemberForm;
import com.webapp.foodgate.form.member.UpdateProfileMemberForm;
import com.webapp.foodgate.mapper.MemberMapper;
import com.webapp.foodgate.repository.ConsumerRepository;
import com.webapp.foodgate.repository.GroupRepository;
import com.webapp.foodgate.repository.ManagerRepository;
import com.webapp.foodgate.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MemberController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * create new admin acc
     *
     * @param signUpMemberForm
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/create_admin", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MEM_C')")
    public ApiMessageDto<String> create(@Valid @RequestBody SignUpMemberForm signUpMemberForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Member member = new Member();

        if (signUpMemberForm.getEmail() != null) {
            member = memberRepository.findMemberByEmail(signUpMemberForm.getEmail()).orElse(null);
            if (member != null) {
                throw new BadRequestException("Email is existed", ErrorCode.MEMBER_ERROR_EMAIL_EXIST);
            }
        }
        if (signUpMemberForm.getLogin() != null) {
            member = memberRepository.findMemberByLogin(signUpMemberForm.getLogin()).orElse(null);
            if (member != null) {
                throw new BadRequestException("Login is used", ErrorCode.MEMBER_ERROR_LOGIN_EXIST);
            }
        }
        if (signUpMemberForm.getPhoneNumber() != null) {
            member = memberRepository.findMemberByPhoneNumber(signUpMemberForm.getPhoneNumber()).orElse(null);
            if (member != null) {
                throw new BadRequestException("phone number is used", ErrorCode.MEMBER_ERROR_PHONE_NUMBER_EXIST);
            }
        }
        member = memberMapper.fromSignUpMemberFormToMember(signUpMemberForm);
        member.setHashPassword(passwordEncoder.encode(signUpMemberForm.getPassword()));

        /**
         * init - member
         **/
        member.setKind(UserBaseConstant.USER_KIND_ADMIN);
        Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_ADMIN);
        member.setGroup(group);
        member.setStatus(UserBaseConstant.STATUS_ACTIVE);
        memberRepository.save(member);
        /**
         * init - admin
         */
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Sign Up new admin Success");
        return apiMessageDto;
    }

    /**
     * @return list MemberAdminDto
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MEM_L')")
    public ApiMessageDto<ResponseListDto<List<MemberAdminDto>>> list(MemberCriteria memberCriteria, Pageable pageable) {
        ApiMessageDto<ResponseListDto<List<MemberAdminDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Member> memberPage = memberRepository.findAll(memberCriteria.getSpecification(), pageable);
        ResponseListDto<List<MemberAdminDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(memberMapper.fromEntityListToMemberAdminDtoList(memberPage.getContent()));
        responseListObj.setTotalPages(memberPage.getTotalPages());
        responseListObj.setTotalElements(memberPage.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list member success");
        return responseListObjApiMessageDto;
    }

    /**
     * @return MemberAdminDto by id
     */
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MEM_V')")
    public ApiMessageDto<MemberAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<MemberAdminDto> apiMessageDto = new ApiMessageDto<>();
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new NotFoundException("Not found member", ErrorCode.MEMBER_ERROR_NOT_FOUND);
        }
        apiMessageDto.setData(memberMapper.fromMemberToMemberAdminDto(member));
        apiMessageDto.setResult(true);
        apiMessageDto.setMessage("Get member success");
        return apiMessageDto;
    }

    /**
     * @param memberCriteria
     * @return list MemberDto
     */
    @GetMapping(value = "/auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListDto<List<MemberDto>>> autoComplete(MemberCriteria memberCriteria) {
        ApiMessageDto<ResponseListDto<List<MemberDto>>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Pageable pageable = PageRequest.of(0, 10);
        memberCriteria.setStatus(UserBaseConstant.STATUS_ACTIVE);
        Page<Member> memberPage = memberRepository.findAll(memberCriteria.getSpecification(), pageable);
        ResponseListDto<List<MemberDto>> responseListObj = new ResponseListDto<>();
        responseListObj.setContent(memberMapper.fromEntityListToDeveloperDtoAutoComplete(memberPage.getContent()));
        responseListObj.setTotalPages(memberPage.getTotalPages());
        responseListObj.setTotalElements(memberPage.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list member success");
        return responseListObjApiMessageDto;
    }

    /**
     * update full member
     *
     * @param updateDeveloperForm
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_MEMBER + "')")
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateMemberForm updateDeveloperForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Member member = memberRepository.findById(updateDeveloperForm.getId()).orElse(null);
        if (member == null) {
            throw new NotFoundException("Not found member", ErrorCode.MEMBER_ERROR_NOT_FOUND);
        }
        Group group = groupRepository.findById(updateDeveloperForm.getGroup_id()).orElse(null);
        if (group == null) {
            throw new NotFoundException("Not found group", ErrorCode.GROUP_ERROR_NOT_FOUND);
        }
        if (!checkValidStatus(updateDeveloperForm.getStatus())) {
            throw new BadRequestException("status invalid", ErrorCode.GROUP_ERROR_INVALID_STATUS);
        }
        if (!checkValidKind(updateDeveloperForm.getKind())) {
            throw new BadRequestException("status kind", ErrorCode.GROUP_ERROR_INVALID_KIND);
        }
        memberMapper.updateMemberFromUpdateMemberForm(updateDeveloperForm, member);
        member.setKind(updateDeveloperForm.getKind());
        member.setStatus(updateDeveloperForm.getStatus());
        member.setGroup(group);
        memberRepository.save(member);
        apiMessageDto.setMessage("Update service member success");
        return apiMessageDto;
    }

    /**
     * update profile admin member
     *
     * @param updateProfileMemberForm
     * @param bindingResult
     * @return
     */
    @PutMapping(value = "/update_profile", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.UPDATE_PROFILE_MEMBER + "')")
    public ApiMessageDto<String> updateProfile(@Valid @RequestBody UpdateProfileMemberForm updateProfileMemberForm, BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findMemberByLogin(authentication.getName()).orElse(null);
        if (member == null) {
            throw new NotFoundException("Not found member", ErrorCode.MEMBER_ERROR_NOT_FOUND);
        }
        memberMapper.updateMemberFromUpdateMemberForm(updateProfileMemberForm, member);
        member.setHashPassword(passwordEncoder.encode(updateProfileMemberForm.getPassword()));
        memberRepository.save(member);
        apiMessageDto.setMessage("Update service member success");
        return apiMessageDto;
    }

    @PutMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.DELETE_MEMBER + "')")
    public ApiMessageDto<String> delete(@PathVariable("id") Long id) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new BadRequestException("Not found member", ErrorCode.MEMBER_ERROR_NOT_FOUND);
        }
        memberRepository.deleteById(id);
        apiMessageDto.setMessage("Delete member success");
        apiMessageDto.setResult(true);
        return apiMessageDto;
    }


    private boolean checkValidStatus(int status) {
        return Arrays.stream(UserBaseConstant.STATUS).anyMatch(validStatus -> validStatus == status);
    }

    private boolean checkValidKind(int kind) {
        return Arrays.stream(UserBaseConstant.KIND).anyMatch(validKind -> validKind == kind);
    }
}

