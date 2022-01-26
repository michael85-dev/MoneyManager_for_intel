package com.hmh.mmp.service;

import com.hmh.mmp.dto.account.AccountSaveDTO;

public interface AccountService {
    Long save(AccountSaveDTO accountSaveDTO);
}
