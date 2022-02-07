package com.hmh.mmp.dto.account;

import com.hmh.mmp.entity.BankEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPagingDTO extends AccountSaveDTO {
    private Long accountId;

    public AccountPagingDTO(Long id, String accountMemo, String accountName, String accountPhotoName, Long minusAsset, Long plusAsset, BankEntity bankEntity, LocalDate calDate, LocalDateTime calTime) {
    }
}
