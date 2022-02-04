package com.hmh.mmp.service;

import com.hmh.mmp.dto.notice.NoticeDetailDTO;
import com.hmh.mmp.dto.notice.NoticePagingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    List<NoticeDetailDTO> findAll();

    Page<NoticePagingDTO> paging(Pageable pageable);

    NoticeDetailDTO findById(Long noticeId);
}
