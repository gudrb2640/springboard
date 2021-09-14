package com.trav.springboard.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private BoardCategory boardCategory;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "board",orphanRemoval = true)
//    private List<Reply> replyList = new ArrayList<>();


    public void modify(String title, String content, BoardCategory boardCategory) {

        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
    }
}
