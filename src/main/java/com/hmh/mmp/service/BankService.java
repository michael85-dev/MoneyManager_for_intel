package com.hmh.mmp.service;

import com.hmh.mmp.dto.BankDetailDTO;
import com.hmh.mmp.dto.BankSaveDTO;
import com.hmh.mmp.dto.MemberDetailDTO;

import java.util.List;

public interface BankService {
    Long save(BankSaveDTO bankSaveDTO);
    List<BankDetailDTO> findAll(Long memberId);
}
