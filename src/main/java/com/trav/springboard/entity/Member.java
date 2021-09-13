package com.trav.springboard.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "boardList")
@Getter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String mid;

    private String nickname;

    private String password;

    @OneToMany( fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "member",orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();
}
