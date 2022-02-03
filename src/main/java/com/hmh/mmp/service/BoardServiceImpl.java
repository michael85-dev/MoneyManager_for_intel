package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.board.BoardDetailDTO;
import com.hmh.mmp.dto.board.BoardPagingDTO;
import com.hmh.mmp.entity.BoardEntity;
import com.hmh.mmp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();

        page = (page == 1) ? 0 : (page - 1);
        Page<BoardEntity> boardEntityList = bor.findAll(PageRequest.of(page, PagingConst.BO_PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPagingDTO> bList =  boardEntityList.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardHits(),
                        board.getBoardContents(),
                        board.getBoardPassword(),
                        board.getBoardTitle(),
                        board.getBoardWriter(),
                        board.getBoardPhotoName(),
                        board.getMemberEntity().getId(),
                        board.getMemberEntity().getMemberNickName())
        );

        return bList;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        System.out.println("BoardServiceImpl.findById");
        // 데이터를 가지고 오고
        BoardEntity boardEntity = bor.findById(boardId).get();
//        데이터를 보내고
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toMoveData(boardEntity);

        return boardDetailDTO;
    }
}
