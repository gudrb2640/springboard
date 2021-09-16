package com.trav.springboard.repository;


import com.trav.springboard.entity.Board;
import com.trav.springboard.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BoardRepository extends JpaRepository<Board,Long>, SearchBoardRepository {

    @Query("select  b, m from Board b left JOIN b.member m where b.bno = :bno")
    Object getBoardWithMember(@Param("bno") Long bno);

    @Query("select b, r from Board b left JOIN Reply r ON b = r.board where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

//    @Query(value = "select b, m,count(r) from Board b " +
//            " left join b.member m " +
//            " left join Reply r on r.board = b " +
//            " group by b",
//            countQuery = "select count (b) from Board b")
//    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, m, count(r) from Board b left join b.member m" +
            " left outer join Reply r on r.board = b" +
            " where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

    @Query("select b.boardCategory,count (b) from Board b group by b.boardCategory")
    List<Object[]>getBoardCategory();
}
