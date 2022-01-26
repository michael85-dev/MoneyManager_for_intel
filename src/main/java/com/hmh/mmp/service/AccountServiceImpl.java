package com.hmh.mmp.service;

import com.hmh.mmp.dto.account.AccountSaveDTO;
import com.hmh.mmp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository ar;


    @Override
    public Long save(AccountSaveDTO accountSaveDTO) {

        
        return null;
    }
}
