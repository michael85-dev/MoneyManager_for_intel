package com.hmh.mmp.dto.balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceUpdateDTO extends BalanceSaveDTO {
    private Long balanceId;

}
