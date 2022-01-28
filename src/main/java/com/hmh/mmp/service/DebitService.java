package com.hmh.mmp.service;

import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.debit.DebitPagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DebitService {
    Page<DebitPagingDTO> paging(Pageable pageable);

    List<DebitDetailDTO> findAll(Long cardId);
}
