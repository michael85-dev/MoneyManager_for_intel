package com.hmh.mmp.service;

import com.hmh.mmp.dto.BankDetailDTO;
import com.hmh.mmp.dto.BankPagingDTO;
import com.hmh.mmp.dto.BankSaveDTO;
import com.hmh.mmp.dto.MemberDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankService {
    Long save(BankSaveDTO bankSaveDTO);
    List<BankDetailDTO> findAll(Long memberId);

    BankDetailDTO findById(Long bankId);

    Page<BankPagingDTO> paging(Pageable pageable);
}
