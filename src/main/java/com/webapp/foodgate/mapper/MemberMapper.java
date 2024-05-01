package com.webapp.foodgate.mapper;


import com.webapp.foodgate.dto.member.MemberAdminDto;
import com.webapp.foodgate.dto.member.MemberDto;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.form.member.SignUpMemberForm;
import com.webapp.foodgate.form.member.UpdateMemberForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {})

public interface MemberMapper {
    /**
     * Mapping from sign up form member to member
     **/
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "isFemale", target = "isFemale")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "login", target = "login")
    @BeanMapping(ignoreByDefault = true)
    Member fromSignUpMemberFormToMember(SignUpMemberForm signUpMemberForm);

    /**
     * Mapping from MemberAdminDto form member to member
     *
     * @param member
     * @return
     */
    @Mapping(source = "id",target="id")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "isFemale", target = "isFemale")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "createdDate", target = "createdDate")
    @BeanMapping(ignoreByDefault = true)
    MemberAdminDto fromMemberToMemberAdminDto(Member member);

    @IterableMapping(elementTargetType = MemberAdminDto.class, qualifiedByName = "fromMemberToMemberAdminDto")
    List<MemberAdminDto> fromEntityListToMemberAdminDtoList(List<Member> members);

    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "isFemale", target = "isFemale")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "imagePath", target = "imagePath")
    @Mapping(source = "login", target = "login")
    @BeanMapping(ignoreByDefault = true)
    MemberDto fromMemberToMemberDto(Member member);

    @IterableMapping(elementTargetType = MemberDto.class, qualifiedByName = "fromMemberToMemberDto")
    List<MemberDto> fromEntityListToDeveloperDtoAutoComplete(List<Member> members);

//    @Mapping(source = "phoneNumber", target = "phoneNumber")
//    @Mapping(source = "isFemale", target = "isFemale")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "firstName", target = "firstName")
//    @Mapping(source = "lastName", target = "lastName")
//    @Mapping(source = "birthDate", target = "birthDate")
//    @Mapping(source = "imagePath", target = "imagePath")
//    @Mapping(source = "login", target = "login")
//    @Mapping(source = "kind", target = "kind")
//    @Mapping(source = "status", target = "status")
//    @BeanMapping(ignoreByDefault = true)
//    void fromUpdateMemberFormToEntity(UpdateMemberForm updateMemberForm,@MappingTarget Member member);


}
