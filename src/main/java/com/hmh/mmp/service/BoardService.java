package com.hmh.mmp.service;

import com.hmh.mmp.dto.BoardDetailDTO;

import java.util.List;

public interface BoardService {
    List<BoardDetailDTO> findAll();
}
