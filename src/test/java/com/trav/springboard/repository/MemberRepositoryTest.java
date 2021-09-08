package com.trav.springboard.repository;

import com.trav.springboard.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    void testInsert() {

        Member member = Member.builder()
                .mid("고형규")
                .password("1234")
                .nickname("Travel")
                .build();

        memberRepository.save(member);

        Optional<Member> result = memberRepository.findById(member.getMno());

        Member member2 = result.get();

        Assertions.assertThat(member2).isEqualTo(member);
    }
}