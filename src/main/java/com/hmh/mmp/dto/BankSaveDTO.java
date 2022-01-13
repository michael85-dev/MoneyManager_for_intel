package com.hmh.mmp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankSaveDTO {

    private Long memberId;
    @Column(length = 50)
    @NotBlank(message = "계좌명은 필수입니다.")
    private String bankName; // 계좌 명
    private String bankAccount; // 계좌번호
    private String bankMemo; // 메모

    private long totalAsset; // 총 잔액
}
