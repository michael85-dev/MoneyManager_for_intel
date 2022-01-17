package com.hmh.mmp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
    private String boardEmail;
    private String boardPassword;
    private String boardName;
    private String boardNickName;
    private String boardMemo;
    private String boardPhotoName;
    private MultipartFile boardPhoto;

}
