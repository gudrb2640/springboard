package com.trav.springboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.QBoard;
import com.trav.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void register(BoardDTO boardDTO) {

        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);

    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(booleanBuilder, pageable);
//        Page<Board> result = boardRepository.findAll(booleanBuilder,pageable);

        Function<Object[],BoardDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getById(boardDTO.getBno());
        board.modify(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getBoardCategory());

        boardRepository.save(board);

    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();


        QBoard qboard = QBoard.board;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = qboard.bno.gt(0L);

        booleanBuilder.and(expression);

        if(type ==null ||type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }

        keyword =keyword.replaceAll("\\p{Z}", ""); //모든 공백 제거

        //검색조건
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {   //title
            conditionBuilder.or(qboard.title.contains(keyword));
        }
        if (type.contains("c")) {   //content
            conditionBuilder.or(qboard.content.contains(keyword));
        }
        if (type.contains("w")) {   //writer
            conditionBuilder.or(qboard.member.nickname.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
