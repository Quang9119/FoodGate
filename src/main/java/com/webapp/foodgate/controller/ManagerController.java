package com.webapp.foodgate.controller;

import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.dto.ApiResponse;
import com.webapp.foodgate.dto.ErrorCode;
import com.webapp.foodgate.entities.Group;
import com.webapp.foodgate.entities.Manager;
import com.webapp.foodgate.entities.Restaurant;
import com.webapp.foodgate.form.manager.CreateManagerForm;
import com.webapp.foodgate.mapper.ManagerMapper;
import com.webapp.foodgate.mapper.MemberMapper;
import com.webapp.foodgate.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/manager")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ManagerController {
    @Autowired
    private RestaurantRepository restaurantRepository;
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



//    @PostMapping(value = "/create_manager", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiResponse<String> createManager(@Valid @RequestBody CreateManagerForm createManagerForm, BindingResult bindingResult) {
//        ApiResponse<String> apiMessageDto = new ApiResponse<>();
//        Manager manager = new Manager();
//
//        if (createManagerForm.getEmail() != null) {
//            manager = managerRepository.findManagerByEmail(createManagerForm.getEmail());
//            if (manager != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_EMAIL_EXIST);
//                apiMessageDto.setMessage("Email is existed");
//                return apiMessageDto;
//            }
//        }
//        if (createManagerForm.getLogin() != null) {
//            manager = managerRepository.findManagerByLogin(createManagerForm.getLogin());
//            if (manager != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_LOGIN_EXIST);
//                apiMessageDto.setMessage("Login is used");
//                return apiMessageDto;
//            }
//        }
//        if (createManagerForm.getPhoneNumber() != null) {
//            manager = managerRepository.findManagerByPhoneNumber(createManagerForm.getPhoneNumber());
//            if (manager != null) {
//                apiMessageDto.setResult(false);
//                apiMessageDto.setCode(ErrorCode.MEMBER_ERROR_PHONE_NUMBER_EXIST);
//                apiMessageDto.setMessage("phone number is used");
//                return apiMessageDto;
//            }
//        }
//        manager = managerMapper.fromSignUpMemberFormToMember(createManagerForm);
//        manager.setHashPassword(passwordEncoder.encode(createManagerForm.getPassword()));
//        /**
//         * init - member
//         **/
//        manager.setKind(UserBaseConstant.USER_KIND_MANAGER);
//        Group group = groupRepository.findFirstByKind(UserBaseConstant.USER_KIND_MANAGER);
//        manager.setGroup(group);
//        manager.setStatus(UserBaseConstant.STATUS_ACTIVE);
//        memberRepository.save(manager);
//        /**
//         * init - manager
//         */
//        Restaurant restaurant = restaurantRepository.findById(createManagerForm.getOwnerRestaurantId()).orElse(null);
//        if (restaurant != null) {
//            apiMessageDto.setResult(false);
//            apiMessageDto.setCode(ErrorCode.RESTAURANT_ERROR_NOT_FOUND);
//            apiMessageDto.setMessage("Not found restaurant");
//            return apiMessageDto;
//        }
//        manager.setOwnerRestaurant(restaurant);
//        managerRepository.save(manager);
//        apiMessageDto.setResult(true);
//        apiMessageDto.setMessage("Sign Up Success");
//        return apiMessageDto;
//    }

}
