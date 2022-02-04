package com.hmh.mmp.controller;

import com.hmh.mmp.dto.notice.NoticeDetailDTO;
import com.hmh.mmp.dto.notice.NoticePagingDTO;
import com.hmh.mmp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private NoticeService ns;

    @GetMapping
    public String findAll(Model model, @PageableDefault(page =1)Pageable pageable) {
        System.out.println("NoticeController.findAll");
        List<NoticeDetailDTO> noticeDetailDTO = ns.findAll();
        model.addAttribute("nDTO", noticeDetailDTO);

        Page<NoticePagingDTO> noticePagingDTOPage = ns.paging(pageable);
        model.addAttribute("noticePage", noticePagingDTOPage);

        int startPage = ;
        int endPage = ;

        return "notice/findAll";
    }
}
