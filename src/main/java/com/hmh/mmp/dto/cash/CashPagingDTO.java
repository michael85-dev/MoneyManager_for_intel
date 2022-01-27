package com.hmh.mmp.dto.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashPagingDTO extends CashSaveDTO{
    private Long cashId;
}
