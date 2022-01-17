package com.hmh.mmp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankPagingDTO {
    private Long bankId;
    private Long memberId;
    private String bankName; // 계좌 명
    private String bankAccount; // 계좌번호
    private String bankMemo; // 메모
    private long totalAsset; // 총 잔액

    public BankPagingDTO(Long bankId, Long memberId, String bankName, String bankMemo, long totalAsset) {
        this.bankId = bankId;
        this.memberId = memberId;
        this.bankName = bankName;
        this.bankMemo = bankMemo;
        this.totalAsset = totalAsset;
    }
}
