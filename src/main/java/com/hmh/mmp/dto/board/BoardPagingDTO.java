package com.hmh.mmp.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPagingDTO extends BoardSaveDTO{
    private Long boardId;

}
