package com.hmh.mmp.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
    private Long memberId;
    private String boardPhotoName;
    private MultipartFile boardPhoto;
    private String boardWriter;
    private String boardContents;
    private String boardTitle;
    private String boardPassword;
    private Integer boardHits;
}
