package com.trav.springboard.service;

import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        Function<Board,BoardDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO getBoardDTO(Long bno) {
        return entityToDTO(boardRepository.getById(bno));
    }
}
