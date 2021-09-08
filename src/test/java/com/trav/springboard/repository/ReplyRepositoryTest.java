package com.trav.springboard.repository;

import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Member;
import com.trav.springboard.entity.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    @Test
    void testInsert() {

        Board board = Board.builder()
                .title("test")
                .content("testContent")
                .member(Member.builder().mid("gudrb").build())
                .build();

        Reply reply = Reply.builder()
                .text("test")
                .board(board)
                .replyer(board.getMember().getMid())
                .build();

        replyRepository.save(reply);

        Assertions.assertThat(reply).isEqualTo(replyRepository.getById(reply.getRno()));
        System.out.println(reply);
    }
}