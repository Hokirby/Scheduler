package com.example.SchedulingApp.Developed.domain.member.repository;

import com.example.SchedulingApp.Developed.domain.member.entity.Member;
import com.example.SchedulingApp.Developed.exception.ApplicationException;
import com.example.SchedulingApp.Developed.exception.ErrorMessageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist Id = " + id));
    }

    Optional<Member> findMemberByName(String memberName);

    default Member findMemberByNameOrElseThrow(String memberName) {
        return findMemberByName(memberName)
                .orElseThrow(() -> new ApplicationException(ErrorMessageCode.NOT_FOUND, "Does Not Exist Member Name = " + memberName));
    }

}
