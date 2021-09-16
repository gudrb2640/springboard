package com.trav.springboard.repository.search;

import com.querydsl.core.Tuple;
import com.trav.springboard.entity.Board;
import com.trav.springboard.entity.BoardCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchBoardRepository {

    Page<Object[]> searchPage(String type, String keyword, BoardCategory boardCategory, Pageable pageable);
}
