package com.trav.springboard.repository;

import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Test
    void testInsert() {

        Board board = Board.builder()
                .title("test")
                .content("testContent")
                .member(Member.builder().mid("gudrb").build())
                .build();

        boardRepository.save(board);

        Assertions.assertThat(board).isEqualTo(boardRepository.getById(board.getBno()));
    }
}