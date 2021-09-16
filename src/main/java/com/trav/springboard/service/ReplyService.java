package com.trav.springboard.service;

import com.trav.springboard.dto.ReplyDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Reply;

import java.util.List;

public interface ReplyService {

    void register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO) {

        Board board = Board.builder().bno(replyDTO.getBno()).build();

        return Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .board(board)
                .build();
    }

    default ReplyDTO entityToDTO(Reply reply) {

        return ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .bno(reply.getBoard().getBno())
                .replyer(reply.getMember().getNickname())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
    }
}
