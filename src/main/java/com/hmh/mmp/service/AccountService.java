package com.hmh.mmp.service;

import com.hmh.mmp.dto.account.AccountDetailDTO;
import com.hmh.mmp.dto.account.AccountSaveDTO;

import java.util.List;

public interface AccountService {
    Long save(AccountSaveDTO accountSaveDTO);

    List<AccountDetailDTO> findAll(Long bankId);
}
