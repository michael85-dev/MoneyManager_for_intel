package com.hmh.mmp.service;

import com.hmh.mmp.dto.board.BoardDetailDTO;
import com.hmh.mmp.entity.BoardEntity;
import com.hmh.mmp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository bor;

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = bor.findAll();

        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity be:boardEntityList) {
            boardDetailDTOList.add(BoardDetailDTO.toMoveData(be));
        }

        return boardDetailDTOList;
    }
}
