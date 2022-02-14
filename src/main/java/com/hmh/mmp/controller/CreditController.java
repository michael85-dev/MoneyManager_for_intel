package com.hmh.mmp.controller;

import com.hmh.mmp.dto.credit.CreditDetailDTO;
import com.hmh.mmp.dto.credit.CreditSaveDTO;
import com.hmh.mmp.dto.credit.CreditUpdateDTO;
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
    private final CreditService cds;
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
        Long creditId = cds.save(creditSaveDTO);

        return "redirect:/card/" + creditSaveDTO.getCardId();
    }

    @GetMapping("{creditId}")
    public String findById(@PathVariable("creditId") Long creditId, Model model) {
        System.out.println("CreditController.findById");

        CreditDetailDTO creditDetailDTO = cds.findById(creditId);
        model.addAttribute("crDTO", creditDetailDTO);

        return "credit/findById";
    }

    @GetMapping("update/{creditId}")
    public String updateForm(@PathVariable("creditId") Long creditId, Model model) {
        System.out.println("CreditController.updateForm");
        CreditDetailDTO creditDetailDTO = cds.findById(creditId);
        model.addAttribute("crDTO", creditDetailDTO);

        return "credit/update";
    }

    @GetMapping("delete/{creditId}")
    public String delete(@PathVariable("creditId") Long creditId) {
        System.out.println("CreditController.delete");
        cds.delete(creditId);

        return "card/findAll";
    }

    @PostMapping("update")
    public String update(@ModelAttribute CreditUpdateDTO creditUpdateDTO) {
        System.out.println("CreditController.update");
        Long creditId = cds.update(creditUpdateDTO);

        return "redirect:/credit/findById"; // credit/ + creditId?
    }
}
