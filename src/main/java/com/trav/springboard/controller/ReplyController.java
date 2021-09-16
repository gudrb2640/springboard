package com.trav.springboard.controller;

import com.trav.springboard.dto.ReplyDTO;
import com.trav.springboard.repository.ReplyRepository;
import com.trav.springboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {

        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> register(@RequestBody ReplyDTO replyDTO) {

        replyService.register(replyDTO);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @DeleteMapping("/{rno}")
    public void remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
    }
}
