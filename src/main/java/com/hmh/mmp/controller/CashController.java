package com.hmh.mmp.controller;

import com.hmh.mmp.dto.cash.CashDetailDTO;
import com.hmh.mmp.dto.cash.CashPagingDTO;
import com.hmh.mmp.dto.cash.CashSaveDTO;
import com.hmh.mmp.dto.member.MemberDetailDTO;
import com.hmh.mmp.service.CashService;
import com.hmh.mmp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static com.hmh.mmp.common.SessionConst.LOGIN_EMAIL;
import static com.hmh.mmp.common.SessionConst.LOGIN_ID;

@Controller
@RequestMapping("/cash/*")
@RequiredArgsConstructor
public class CashController {
    private final CashService css;
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("csave", new CashSaveDTO());

        return "cash/save";
    }

    @PostMapping("save")
    public String save(@PathVariable @ModelAttribute("csave") CashSaveDTO cashSaveDTO, HttpSession session, BindingResult br) throws IOException {
        String memberEmail = (String)session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberEmail);
        Long memberId = memberDetailDTO.getMemberId();

        cashSaveDTO.setMemberId(memberId);

        Long cashId = css.save(cashSaveDTO);

        return "cash/findAll";
    }

    @GetMapping
    public String findAll(Model model, @PageableDefault(page = 1)Pageable pageable, HttpSession session) {
        Long memberId = (Long)session.getAttribute(LOGIN_ID);
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);

        List<CashDetailDTO> cashDetailDTOList = css.findAll(memberId);
        model.addAttribute("cashList", cashDetailDTOList);

        Page<CashPagingDTO> cashPagingDTOList = css.paging(pageable);

        return "cash/findAll";
    }

}
