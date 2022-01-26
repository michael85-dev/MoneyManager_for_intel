package com.hmh.mmp.service;

import com.hmh.mmp.dto.board.BoardDetailDTO;
import com.hmh.mmp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository bor;

    @Override
    public List<BoardDetailDTO> findAll() {
        bor.findAll();

        return null;
    }
}
