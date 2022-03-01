package com.hmh.mmp.dto.balance;

import com.hmh.mmp.entity.BalanceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDetailDTO extends BalanceSaveDTO {
    private Long balanceId;

    public static BalanceDetailDTO toMoveData(BalanceEntity balanceEntity) {
        BalanceDetailDTO balanceDetailDTO = new BalanceDetailDTO();
        balanceDetailDTO.setBalanceId(balanceEntity.getId());
        balanceDetailDTO.setBalanceMemo(balanceEntity.getBalanceMemo());
        balanceDetailDTO.setBalanceName(balanceEntity.getBalanceName());
        balanceDetailDTO.setCashId(balanceEntity.getCashEntity().getId());
        balanceDetailDTO.setBalancePhotoName(balanceEntity.getBalancePhotoName());
        balanceDetailDTO.setMinusAsset(balanceEntity.getMinusAsset());
        balanceDetailDTO.setPlusAsset(balanceEntity.getPlusAsset());

        return balanceDetailDTO;
    }
}
