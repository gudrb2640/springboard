package com.trav.springboard.service;

import com.trav.springboard.dto.MemberDTO;
import com.trav.springboard.entity.Member;
import com.trav.springboard.entity.MemberRole;
import com.trav.springboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        member.addMemberRole(MemberRole.USER);
        memberRepository.save(member);

        return member.getMno();

    }

}
