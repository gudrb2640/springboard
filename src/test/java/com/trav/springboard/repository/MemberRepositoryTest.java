package com.trav.springboard.repository;

import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testInsert() {

        IntStream.rangeClosed(1,300).forEach(i ->{
            Member member = Member.builder()
                    .mid("TestID"+i)
                    .password("1234")
                    .nickname("TestNick"+i)
                    .build();

            memberRepository.save(member);
        });
    }

}