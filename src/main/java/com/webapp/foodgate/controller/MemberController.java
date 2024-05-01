package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.dto.ApiMessageDto;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.dto.ResponseListDto;
import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.member.MemberDto;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.entities.criteria.MemberCriteria;
import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.form.member.UpdateMemberForm;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
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
//    @PreAuthorize("hasRole('MEM_C')")
//    @PreAuthorize("hasAuthority('MEM_C')")
    public ApiResponse<String> create(@Valid @RequestBody SignUpMemberForm signUpMemberForm, BindingResult bindingResult) {
        ApiResponse<String> apiMessageDto = new ApiResponse<>();
        Member member = new Member();

        if (signUpMemberForm.getEmail() != null) {
            member = memberRepository.findMemberByEmail(signUpMemberForm.getEmail()).orElse(null);
            if (member != null) {
                apiMessageDto.setResult(false);
                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_EMAIL_EXIST);
                apiMessageDto.setMessage("Email is existed");
                return apiMessageDto;
            }
        }
        if (signUpMemberForm.getLogin() != null) {
            member = memberRepository.findMemberByLogin(signUpMemberForm.getLogin()).orElse(null);
            if (member != null) {
                apiMessageDto.setResult(false);
                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_LOGIN_EXIST);
                apiMessageDto.setMessage("Login is used");
                return apiMessageDto;
            }
        }
        if (signUpMemberForm.getPhoneNumber() != null) {
            member = memberRepository.findMemberByPhoneNumber(signUpMemberForm.getPhoneNumber()).orElse(null);
            if (member != null) {
                apiMessageDto.setResult(false);
                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_PHONE_NUMBER_EXIST);
                apiMessageDto.setMessage("phone number is used");
                return apiMessageDto;
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
    @PreAuthorize("hasRole('MEM_V')")
    public ApiMessageDto<MemberAdminDto> get(@PathVariable("id") Long id) {
        ApiMessageDto<MemberAdminDto> apiMessageDto = new ApiMessageDto<>();
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_NOT_FOUND);
            apiMessageDto.setMessage("Not found member");
            return apiMessageDto;
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

//    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('UPDATE_MEMBER')")
//    public ApiMessageDto<String> update(@Valid @RequestBody UpdateMemberForm updateDeveloperForm, BindingResult bindingResult){
//
//    }

}

