package com.hmh.mmp.dto.credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditSaveDTO {
    private Long cardId;
    private Long bankId;
    private Long memberId;

    private LocalDate date;
    private String creditName; // 내역
    private String creditPhotoName; // 사진명
    private String creditMemo;
    private Long minusAsset; // 지출
    private Double rate; // 할부이자.
    private Integer month; // 할부
    private Double creditGet; // 캐쉬백
}
