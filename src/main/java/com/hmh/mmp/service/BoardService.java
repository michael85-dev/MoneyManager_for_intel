package com.hmh.mmp.service;

import com.hmh.mmp.dto.board.BoardDetailDTO;
import com.hmh.mmp.dto.board.BoardPagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDetailDTO> findAll();

    Page<BoardPagingDTO> paging(Pageable pageable);

    BoardDetailDTO findById(Long boardId);
}
