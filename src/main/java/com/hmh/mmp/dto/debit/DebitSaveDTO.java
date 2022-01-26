package com.hmh.mmp.dto.debit;

import com.hmh.mmp.entity.BankEntity;
import com.hmh.mmp.entity.CardEntity;
import com.hmh.mmp.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

// 체크카드 관련 정보
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitSaveDTO {
    private Long cardId;
    private Long memberId;
    private Long bankId;

    private LocalDate date;
    private String debitName; //내역명
    private MultipartFile debitPhoto;
    private String debitPhotoName; // 영수증
    private Long minusAsset; // 지출
    private String debitMemo; // 내역
    private Double debitGet; // 할인
}
