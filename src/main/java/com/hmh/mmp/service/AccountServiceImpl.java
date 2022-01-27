package com.hmh.mmp.service;

import com.hmh.mmp.dto.account.AccountDetailDTO;
import com.hmh.mmp.dto.account.AccountSaveDTO;
import com.hmh.mmp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository ar;


    @Override
    public Long save(AccountSaveDTO accountSaveDTO) {
        MultipartFile accountPhoto = accountSaveDTO.getAccountPhoto();
        String accountPhotoName = accountPhoto.getOriginalFilename();
        accountPhotoName = System.currentTimeMillis() + "-" + accountPhotoName;

        String savePath = "/Users/myungha/Desktop/Github/MoneyManager_for_intel/src/main/resources/photo/account" + accountPhotoName;
        if (!accountPhoto.isEmpty()) {
            accountPhoto.transferTo(new File(savePath));
            accountSaveDTO.setAccountPhotoName();
        }

        return null;
    }

    @Override
    public List<AccountDetailDTO> findAll(Long bankId) {
        return null;
    }
}
