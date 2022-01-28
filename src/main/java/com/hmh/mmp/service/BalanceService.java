package com.hmh.mmp.service;

import com.hmh.mmp.dto.balance.BalanceDetailDTO;
import com.hmh.mmp.dto.balance.BalancePagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BalanceService {
    List<BalanceDetailDTO> findAll(Long cashId);

    Page<BalancePagingDTO> paging(Pageable pageable);
}
