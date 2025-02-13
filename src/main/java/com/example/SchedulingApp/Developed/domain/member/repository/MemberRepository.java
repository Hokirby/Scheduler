package com.example.SchedulingApp.Developed.domain.member.repository;

import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist Id = " + id));
    }

    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email) {
        return findMemberByEmail(email)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist email = " + email));
    }
}
