package com.trav.springboard.dto;

import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private Long mno;

    private String mid;

    private String password;

    private String nickname;

    private List<Board> boardList;
}
