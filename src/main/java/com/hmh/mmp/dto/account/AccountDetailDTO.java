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
        accountDetailDTO.setFirstList(accountEntity.getFirstListEntityList()); // 이렇게 설정하면 그 안에 있는 항목을 list로 불러오지 못할텐데.

        return accountDetailDTO;
    }
}
