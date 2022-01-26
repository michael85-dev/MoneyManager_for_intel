package com.hmh.mmp.dto.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashDetailDTO {
    private Long cashId;
    private String cashName;
    private Long memberId;
    private String cashMemo;
    private Long totalAsset;
    private String cashPhotoName;
}
