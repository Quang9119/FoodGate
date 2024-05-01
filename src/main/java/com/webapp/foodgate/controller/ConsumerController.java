package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.entities.Consumer;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.form.consumer.SignUpConsumerForm;
import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.mapper.ConsumerMapper;
import com.webapp.foodgate.mapper.MemberMapper;
import com.webapp.foodgate.repository.ConsumerRepository;
import com.webapp.foodgate.repository.GroupRepository;
import com.webapp.foodgate.repository.ManagerRepository;
import com.webapp.foodgate.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/consumer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ConsumerController {
//    @Autowired
//    private GroupRepository groupRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private ManagerRepository managerRepository;
//    @Autowired
//    private ConsumerRepository consumerRepository;
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private ConsumerMapper consumerMapper;
//
//    /**
//     * sign up new consumer in system
//     *
//     * @param signUpConsumerForm
//     * @param bindingResult
//     * @return
//     */
//    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<String> create(@Valid @RequestBody SignUpConsumerForm signUpConsumerForm,
//                                      BindingResult bindingResult) {
//        ApiResponse<String> apiMessageDto = new ApiResponse<>();
//        Member member = new Member();
//
//        if (signUpConsumerForm.getEmail() != null) {
//            member = memberRepository.findMemberByEmail(signUpConsumerForm.getEmail()).orElse(null);
//            if (member != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_EMAIL_EXIST);
//                apiMessageDto.setMessage("Email is existed");
//                return apiMessageDto;
//            }
//        }
//        if (signUpConsumerForm.getLogin() != null) {
//            member = memberRepository.findMemberByLogin(signUpConsumerForm.getLogin()).orElse(null);
//            if (member != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_LOGIN_EXIST);
//                apiMessageDto.setMessage("Login is used");
//                return apiMessageDto;
//            }
//        }
//        if (signUpConsumerForm.getPhoneNumber() != null) {
//            member = memberRepository.findMemberByPhoneNumber(signUpConsumerForm.getPhoneNumber()).orElse(null);
//            if (member != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_PHONE_NUMBER_EXIST);
//                apiMessageDto.setMessage("phone number is used");
//                return apiMessageDto;
//            }
//        }
//        member = memberMapper.fromSignUpMemberFormToMember(signUpConsumerForm);
//        member.setHashPassword(passwordEncoder.encode(signUpConsumerForm.getPassword()));
//
//        /**
//         * init - member
//         **/
//        member.setKind(UserBaseConstant.USER_KIND_CONSUMER);
//        Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_CONSUMER);
//        member.setGroup(group);
//        member.setStatus(UserBaseConstant.STATUS_ACTIVE);
//        memberRepository.save(member);
//        /**
//         * init - consumer
//         */
//        Consumer consumer = new Consumer();
//        consumer.setMember(member);
//        consumer.setLovedFoods(null);
//        consumer.setBalanceWallet(1000000L);
//        consumerRepository.save(consumer);
//
//        apiMessageDto.setResult(true);
//        apiMessageDto.setMessage("Sign Up Success");
//        return apiMessageDto;
//    }
}
