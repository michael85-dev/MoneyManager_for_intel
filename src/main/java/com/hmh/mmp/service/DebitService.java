package com.hmh.mmp.service;

import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.debit.DebitPagingDTO;
import com.hmh.mmp.dto.debit.DebitSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface DebitService {
    Page<DebitPagingDTO> paging(Pageable pageable);

    List<DebitDetailDTO> findAll(Long cardId);

    Long save(DebitSaveDTO debitSaveDTO) throws IOException;
}
