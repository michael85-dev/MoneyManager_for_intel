package com.hmh.mmp.controller;

import com.hmh.mmp.dto.credit.CreditSaveDTO;
import com.hmh.mmp.service.BankService;
import com.hmh.mmp.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/credit/*")
public class CreditController {
    private final CreditService crs;
    private final BankService bs;

    @GetMapping("save/{cardId}")
    public String saveForm(@PathVariable("cardId") Long cardId, Model model) {
        System.out.println("CreditController.saveForm");
        CreditSaveDTO creditSaveDTO = new CreditSaveDTO();
        creditSaveDTO.setCardId(cardId);
        model.addAttribute("crsave", creditSaveDTO);

        return "credit/save"; // save 개선 필요함
    }

    @PostMapping("save")
    public String save(@ModelAttribute CreditSaveDTO creditSaveDTO) {
        System.out.println("CreditController.save");
        Long creditId = crs.save(creditSaveDTO);

        return "redirect:/card/" + creditSaveDTO.getCardId();
    }
}
