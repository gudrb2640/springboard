package com.trav.springboard.repository;

import com.querydsl.core.BooleanBuilder;
import com.trav.springboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {

    @Query("select  b, m from Board b left JOIN b.member m where b.bno = :bno")
    Object getBoardWithMember(@Param("bno") Long bno);

    @Query("select b, r from Board b left JOIN Reply r ON b = r.board where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, m,count(r) from Board b " +
            " left join b.member m " +
            " left join Reply r on r.board = b " +
            " group by b",
            countQuery = "select count (b) from Board b")
    Page<Object[]> getBoardWithReplyCount(BooleanBuilder booleanBuilder,Pageable pageable);

}
