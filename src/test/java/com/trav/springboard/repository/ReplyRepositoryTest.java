package com.trav.springboard.repository;

import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Member;
import com.trav.springboard.entity.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;


    @Test
    void testInsert() {

        IntStream.rangeClosed(1,300).forEach(i ->{

            Long bno = (long)(((Math.random()*100)+1));
            Long mno = (long)(((Math.random()*100)+1));
            Member member = Member.builder().mno(mno).build();
            Board board = Board.builder().bno(bno).build();


            Reply reply = Reply.builder()
                    .text("replyTest"+i)
                    .board(board)
                    .member(member)
                    .build();

            replyRepository.save(reply);


        });
    }

    @Test
    void getReplyList() {

        Board board = Board.builder()
                .bno(24L)
                .build();

        List<Reply> result = replyRepository.getReplyByBoardOrderByRno(board);

       result.forEach(System.out::println);
    }
}