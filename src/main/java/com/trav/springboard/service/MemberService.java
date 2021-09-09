package com.trav.springboard.service;

import com.trav.springboard.dto.MemberDTO;
import com.trav.springboard.entity.Member;

public interface MemberService{

    Long register(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO) {

        return Member.builder()
               .mno(memberDTO.getMno())
               .mid(memberDTO.getMid())
               .password(memberDTO.getPassword())
               .nickname(memberDTO.getNickname())
               .build();
    }
}
