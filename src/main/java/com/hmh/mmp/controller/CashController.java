package com.hmh.mmp.controller;

import com.hmh.mmp.dto.cash.CashSaveDTO;
import com.hmh.mmp.dto.member.MemberDetailDTO;
import com.hmh.mmp.service.CashService;
import com.hmh.mmp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.hmh.mmp.common.SessionConst.LOGIN_EMAIL;

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
    public String save(@PathVariable @ModelAttribute("save") CashSaveDTO cashSaveDTO, HttpSession session, BindingResult br) {
        String memberEmail = (String)session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberEmail);
        Long memberId = memberDetailDTO.getMemberId();

        cashSaveDTO.setMemberId(memberId);

        Long cashId = css.save(cashSaveDTO);

        return "cash/findAll";
    }
}
