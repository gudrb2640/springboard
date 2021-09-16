package com.trav.springboard.service;

import com.trav.springboard.dto.BoardDTO;
import com.trav.springboard.dto.PageRequestDTO;
import com.trav.springboard.dto.PageResultDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.BoardCategory;
import com.trav.springboard.entity.Member;

import java.util.List;
import java.util.Map;

public interface BoardService {

    void register(BoardDTO boardDTO);

    BoardDTO read(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    Map<BoardCategory,Long> getBoardCategory();
    PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO requestDTO);

    default Board dtoToEntity(BoardDTO boardDTO) {

        return Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .boardCategory(boardDTO.getBoardCategory())
                .build();
    }

    default BoardDTO entityToDTO(Board board,Member member,Long replyCnt) {

        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(member.getNickname())
                .boardCategory(board.getBoardCategory())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCnt(replyCnt.intValue())
                .build();
    }


}



