package com.hmh.mmp.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailDTO {
    private Long accountId;
    private Long bankId;
    private Long memberId;
    private Long plusAsset;
    private Long minusAsset;
    private String accountName;
    private MultipartFile accountPhoto;
    private String accountPhotoName;
    private String accountMemo;
}
