package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.notice.NoticeDetailDTO;
import com.hmh.mmp.dto.notice.NoticePagingDTO;
import com.hmh.mmp.entity.NoticeEntity;
import com.hmh.mmp.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository nr;

    @Override
    public List<NoticeDetailDTO> findAll() {
        List<NoticeEntity> noticeEntityList = nr.findAll();
        List<NoticeDetailDTO> noticeDetailDTOList = new ArrayList<>();

        for (NoticeEntity ne: noticeEntityList) {
            noticeDetailDTOList.add(NoticeDetailDTO.toDataMove(ne));
        }

        return noticeDetailDTOList;
    }

    @Override
    public Page<NoticePagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();;
        page = (page == 1) ? 0 : (page - 1);

        Page<NoticeEntity> noticeEntityPage = nr.findAll(PageRequest.of(page, PagingConst.N_PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<NoticePagingDTO> noticePagingDTOPage = noticeEntityPage.map(
                notice -> new NoticePagingDTO(notice.getId(),
                        notice.getNoticeHits(),
                        notice.getNoticeContents(),
                        notice.getNoticePassword(),
                        notice.getNoticeTitle(),
                        notice.getNoticeWriter(),
                        notice.getNoticePhotoName(),
                        notice.getMemberEntity().getId())
        );

        return noticePagingDTOPage;
    }
}
