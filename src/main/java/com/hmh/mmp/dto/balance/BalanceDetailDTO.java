package com.hmh.mmp.dto.balance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDetailDTO extends BalanceSaveDTO{
    private Long balanceId;
}
