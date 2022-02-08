package com.hmh.mmp.controller;

import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.debit.DebitSaveDTO;
import com.hmh.mmp.service.BankService;
import com.hmh.mmp.service.CardService;
import com.hmh.mmp.service.DebitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/debit/*")
public class DebitController {
    private final DebitService ds;
    private final CardService cs;
    private final BankService bs;

    @GetMapping("save/{cardId}")
    public String saveForm(@PathVariable("cardId") Long cardId, Model model) {
        System.out.println("DebitController.saveForm");
        DebitSaveDTO debitSaveDTO = new DebitSaveDTO();
        debitSaveDTO.setCardId(cardId);

        model.addAttribute("dsave", debitSaveDTO);

        return "debit/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute DebitSaveDTO debitSaveDTO) throws IOException {
        System.out.println("DebitController.save");
        Long debitId = ds.save(debitSaveDTO);

        return "redirect:/card/findAll";
    }

    @GetMapping("{debitId}")
    public String findById(@PathVariable("debitId") Long debitId, Model model) {
        System.out.println("DebitController.findById");
        

        return "debit/findById";
    }

}
