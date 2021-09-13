package com.trav.springboard.repository;

import com.trav.springboard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select b from Board b")
    Page<Object[]> getListPage(Pageable pageable);
}
