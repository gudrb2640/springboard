package com.trav.springboard.dto;


import com.trav.springboard.entity.BoardCategory;
import com.trav.springboard.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private BoardCategory boardCategory;

    //멤버
    private Long mno;

    private String nickname;

    //리뷰 수 jpa의 count()
    private int replyCnt;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
