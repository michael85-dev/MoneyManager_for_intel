package com.hmh.mmp.service;

import com.hmh.mmp.dto.cash.CashDetailDTO;
import com.hmh.mmp.dto.cash.CashPagingDTO;
import com.hmh.mmp.dto.cash.CashSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CashService {
    Long save(CashSaveDTO cashSaveDTO) throws IOException;

    List<CashDetailDTO> findAll(Long memberId);

    Page<CashPagingDTO> paging(Pageable pageable);
}
