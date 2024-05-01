package com.webapp.foodgate.repository;

import com.webapp.foodgate.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findMemberByLogin(String login);

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberByPhoneNumber(String phoneNumber);

    boolean existsByKind(int kind);

    boolean existsByLogin(String login);
}
