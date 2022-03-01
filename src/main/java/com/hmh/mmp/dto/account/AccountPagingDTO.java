package com.hmh.mmp.dto.account;

import com.hmh.mmp.entity.BankEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPagingDTO extends AccountSaveDTO {
    private Long accountId;



    public AccountPagingDTO(Long id, String accountMemo, String accountName, String accountPhotoName, Long minusAsset, Long plusAsset, BankEntity bankEntity, LocalDate calDate, LocalDateTime calTime) {
    }
}
