package com.hmh.mmp.dto.account;

import com.hmh.mmp.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailDTO extends AccountSaveDTO {
    private Long accountId;

    public static AccountDetailDTO toMoveData(AccountEntity accountEntity) {
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setAccountId(accountEntity.getId());
        accountDetailDTO.setAccountName(accountEntity.getAccountName());
        accountDetailDTO.setAccountMemo(accountEntity.getAccountMemo());
        accountDetailDTO.setMinusAsset(accountEntity.getMinusAsset());
        accountDetailDTO.setPlusAsset(accountEntity.getPlusAsset());
        accountDetailDTO.setAccountPhotoName(accountEntity.getAccountPhotoName());
        accountDetailDTO.setBankId(accountEntity.getBankEntity().getId());
        accountDetailDTO.setMemberId(accountEntity.getMemberEntity().getId());

        return accountDetailDTO;
    }
}
