package com.trav.springboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.BoardCategory;
import com.trav.springboard.entity.Member;
import com.trav.springboard.entity.QBoard;
import com.trav.springboard.repository.BoardRepository;
import com.trav.springboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public void register(BoardDTO boardDTO) {

        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);

    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {



        Function<Object[],BoardDTO> fn = (en -> entityToDTO((Board) en[0],(Member) en[1], (Long) en[2]));

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Page<Object[]> result = boardRepository.searchPage(requestDTO.getType(), requestDTO.getKeyword(),requestDTO.getBoardCategory(),pageable);

        return new PageResultDTO<>(result, fn);

    }

    @Override
    public BoardDTO read(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getById(boardDTO.getBno());
        board.modify(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getBoardCategory());

        boardRepository.save(board);

    }

    @Transactional
    @Override
    public void remove(Long bno) {
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Override
    public Map<BoardCategory, Long> getBoardCategory() {

        List<Object[]> result = boardRepository.getBoardCategory();
        Map<BoardCategory, Long> map = new LinkedHashMap<>();
        result.forEach(arr ->{
            map.put((BoardCategory) arr[0], (Long) arr[1]);
        });

        return map;
    }
}
