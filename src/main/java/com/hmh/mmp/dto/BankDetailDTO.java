package com.hmh.mmp.dto;

import com.hmh.mmp.entity.BankEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class BankDetailDTO {

    private Long bankId;

    private Long memberId;

    @Column(length = 50)
    @NotBlank(message = "계좌명은 필수입니다.")
    private String bankName; // 계좌 명
    private String bankAccount; // 계좌번호
    private String bankMemo; // 메모

    private long totalAsset; // 총 잔액

    public static BankDetailDTO toBankDetail(BankEntity bankEntity) {
        BankDetailDTO bankDetailDTO = new BankDetailDTO();
        bankDetailDTO.setBankId(bankEntity.getId());
        bankDetailDTO.setBankName(bankEntity.getBankName());
        bankDetailDTO.setBankMemo(bankEntity.getBankMemo());
        bankDetailDTO.setTotalAsset(bankEntity.getTotalAsset());
        // ?? 이걸 어떻게 해야하나.
        bankDetailDTO.setMemberId(bankEntity.getMemberEntity().getId());

        return bankDetailDTO;
    }
}
