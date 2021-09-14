package com.trav.springboard.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.*;
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
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


    @Test
    void testInsert() {

        IntStream.rangeClosed(1,300).forEach(i->{

            Board board = Board.builder()
                    .title("Title" + i)
                    .content("content"+i)
                    .boardCategory(BoardCategory.randomCategory())
                    .member(Member.builder().mno((long) i).build())
                    .build();

            boardRepository.save(board);
        });
    }


    @Transactional
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

    @Test
    void querydsl() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        QBoard qBoard = QBoard.board;

        String keyword = "11 ";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qBoard.title.contains(keyword);
        BooleanExpression exContent = qBoard.content.contains(keyword);
        BooleanExpression exCategory = qBoard.boardCategory.stringValue().contains(keyword);
        BooleanExpression exNickname = qBoard.member.nickname.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent).or(exCategory).or(exNickname);

        builder.and(exAll);
        builder.and(qBoard.bno.gt(0L));

        Page<Board> result = boardRepository.findAll(builder, pageable);
        Stream<Board> boardStream = result.get();
        boardStream.forEach(board -> {
            System.out.println(board.getTitle());
        });

    }

    @Test
    void getBoardWithMember() {
        Long bno = 5L;
        Object result = boardRepository.getBoardWithMember(bno);
        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));

    }


    @Test
    void getBoardWithReply() {
        Long bno = 53L;
        List<Object[]> result = boardRepository.getBoardWithReply(bno);
        result.forEach(reply ->{
            System.out.println(Arrays.toString(reply));
        });
    }

    @Test
    void getBoardWithReplyCount() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno"));

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row ->{
            Object[] arr = (Object[]) row;

            System.out.println(Arrays.toString(arr));
        });
    }

}