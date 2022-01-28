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
import java.util.Optional;

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

        Long bankTotalAsset = br.findById(accountSaveDTO.getBankId()).get().getTotalAsset();
        bankTotalAsset = bankTotalAsset + accountSaveDTO.getPlusAsset() - accountSaveDTO.getMinusAsset();

        BankEntity bankEntity = br.findById(accountSaveDTO.getBankId()).get();

        // account저장으로 인해 생긴 금액의 차액을 정산하여 최종 금액으로 반영
        bankEntity.setTotalAsset(bankTotalAsset);

        AccountEntity accountEntity = AccountEntity.toSaveData(accountSaveDTO, bankEntity);

        Long accountId = ar.save(accountEntity).getId();

        return accountId;
    }

    @Override
    public List<AccountDetailDTO> findAll(Long bankId) {
        Optional<BankEntity> bankEntityOptional = br.findById(bankId);
        BankEntity bankEntity = bankEntityOptional.get();

        List<AccountEntity> accountEntityList = bankEntity.getAccountEntityList();

//        List<AccountEntity> accountEntityList = ar.findAll(bankId);
        List<AccountDetailDTO> accountDetailDTOList = new ArrayList<>();

        for (AccountEntity ae:accountEntityList) {
            accountDetailDTOList.add(AccountDetailDTO.toMoveData(ae));
        }

        return accountDetailDTOList;
    }
}
