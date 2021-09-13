package com.trav.springboard.repository;

import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Member;
import com.trav.springboard.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

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

    @Test
    void getList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO :
                resultDTO.getDtoList()) {
            System.out.println(boardDTO);
        }
    }


}