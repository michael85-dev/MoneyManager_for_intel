package com.hmh.mmp.service;

import com.hmh.mmp.dto.credit.CreditDetailDTO;
import com.hmh.mmp.dto.credit.CreditPagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreditService {
    Page<CreditPagingDTO> paging(Pageable pageable);

    List<CreditDetailDTO> findAll(Long cardId);
}
