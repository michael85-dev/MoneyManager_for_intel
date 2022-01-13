package com.hmh.mmp.controller;

import com.hmh.mmp.dto.*;
import com.hmh.mmp.service.BankService;
import com.hmh.mmp.service.MemberService;
import com.hmh.mmp.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.hmh.mmp.common.SessionConst.LOGIN_ID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bank/*")
public class BankController {
    private static MemberService ms;
    private static BankService bs;

    @GetMapping("save")
    public String saveForm(Model model, HttpSession session, MemberLoginDTO memberLoginDTO) {
        // 해당 값을 가지고 와야 하는데 해당 LoginData 에서 가지고 와야하는지?
        Long memberId = memberLoginDTO.getId();
        System.out.println("로그인 된 memberId : " + memberId);

        model.addAttribute("memberId", memberId);
        model.addAttribute("msave", session.getAttribute(LOGIN_ID));
        model.addAttribute("bsave", new BankSaveDTO());

        return "/bank/save";
    }

    @PostMapping("save")
    public String save(@PathVariable @ModelAttribute("bank") BankSaveDTO bankSaveDTO, BindingResult br) {
        if (br.hasErrors()) {

            return "bank/save";
        }

        try {
            bs.save(bankSaveDTO);
        } catch (IllegalStateException e) {
            br.reject("bCheck", e.getMessage());
        }

        return "main";
    }

    @GetMapping
    public String findAll(Model model, HttpSession session, MemberLoginDTO memberLoginDTO) {
        Long memberId = memberLoginDTO.getId();

        List<BankDetailDTO> bankDetailDTOList = bs.findAll(memberId);

        model.addAttribute("bdList", bankDetailDTOList);
        return "/bank/findAll";
    }
}
