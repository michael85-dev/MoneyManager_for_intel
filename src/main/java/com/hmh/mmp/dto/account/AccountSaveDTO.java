package com.hmh.mmp.dto.account;

import com.hmh.mmp.entity.BankEntity;
import com.hmh.mmp.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSaveDTO{
    private Long bankId;
    private Long memberId;

    private Long plusAsset;
    private Long minusAsset;
    private String accountName;
    private MultipartFile accountPhoto;
    private String accountPhotoName;
    private String accountMemo;

}
