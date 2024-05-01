package com.webapp.foodgate.service;


import com.webapp.foodgate.constant.UserBaseConstant;
import com.webapp.foodgate.entities.Member;
import com.webapp.foodgate.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public DomainUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        if (new EmailValidator().isValid(login, null)) {
            return memberRepository.findMemberByEmail(login)
                    .map(member -> createSpringSecurityUser(login, member))
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + login
                            + " was not found in the database"));
        }
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return memberRepository.findMemberByLogin(lowercaseLogin)
                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser
            (String lowercaseLogin, Member member) {
        if (member.getStatus() != UserBaseConstant.STATUS_ACTIVE) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = member.getGroup().getPermissions().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPCode()))
                .collect(Collectors.toList());
        return new CustomUserDetail(member.getLogin(),
                member.getHashPassword(),
                grantedAuthorities, member.getId().toString());
    }

}