package com.hmh.mmp.dto.balance;

import com.hmh.mmp.entity.CashEntity;
import com.hmh.mmp.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// 현금 관련 내역을 보기 위한 것.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceSaveDTO {
    private Long cashId;
    private Long memberId;
    private String balanceMemo;
    private String balanceName;
    private long plusAsset;
    private long minusAsset;
    private MultipartFile balancePhoto;
    private String balancePhotoName;
}
