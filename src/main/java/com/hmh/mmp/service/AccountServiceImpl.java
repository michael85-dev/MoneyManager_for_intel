package com.hmh.mmp.service;

import com.hmh.mmp.dto.account.AccountDetailDTO;
import com.hmh.mmp.dto.account.AccountSaveDTO;
import com.hmh.mmp.entity.AccountEntity;
import com.hmh.mmp.entity.BankEntity;
import com.hmh.mmp.repository.AccountRepository;
import com.hmh.mmp.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository ar;
    private final BankRepository br;

    @Override
    public Long save(AccountSaveDTO accountSaveDTO) throws IOException {
        MultipartFile accountPhoto = accountSaveDTO.getAccountPhoto();
        String accountPhotoName = accountPhoto.getOriginalFilename();
        accountPhotoName = System.currentTimeMillis() + "-" + accountPhotoName;

        String savePath = "/Users/myungha/Desktop/Github/MoneyManager_for_intel/src/main/resources/photo/account" + accountPhotoName;
        if (!accountPhoto.isEmpty()) {
            accountPhoto.transferTo(new File(savePath));
            accountSaveDTO.setAccountPhotoName(accountPhotoName);
        }
        if (accountSaveDTO.getPlusAsset() != null) {
            accountSaveDTO.setMinusAsset(0L);
        } else {
            accountSaveDTO.setPlusAsset(0L);
        }

        BankEntity bankEntity = br.findById(accountSaveDTO.getBankId()).get();

        AccountEntity accountEntity = AccountEntity.toSaveData(accountSaveDTO, bankEntity);

        Long accountId = ar.save(accountEntity).getId();

        return accountId;
    }

    @Override
    public List<AccountDetailDTO> findAll(Long bankId) {
        List<AccountEntity> accountEntityList = ar.findAll(bankId);
        List<AccountDetailDTO> accountDetailDTOList = new ArrayList<>();

        for (AccountEntity ae:accountEntityList) {
            accountDetailDTOList.add(AccountDetailDTO.toMoveData(ae));
        }

        return accountDetailDTOList;
    }
}
