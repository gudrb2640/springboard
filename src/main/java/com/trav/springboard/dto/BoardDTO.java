package com.trav.springboard.dto;


import com.trav.springboard.entity.Reply;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private List<Reply> replyList;

}
