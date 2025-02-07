package com.example.SchedulingApp.Developed.member.repository;

import com.example.SchedulingApp.Developed.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }
    Optional<Member> findMemberByName(String memberName);

    default Member findMemberByNameOrElseThrow(String memberName) {
        return findMemberByName(memberName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does Not Exist Member Name = " + memberName));
    }

}
