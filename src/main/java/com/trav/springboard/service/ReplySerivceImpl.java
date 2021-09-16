package com.trav.springboard.service;

import com.trav.springboard.dto.ReplyDTO;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Reply;
import com.trav.springboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplySerivceImpl implements ReplyService{

    private final ReplyRepository replyRepository;


    @Override
    public void register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);


    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result = replyRepository.getReplyByBoardOrderByRno(Board.builder().bno(bno).build());

        return result.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
