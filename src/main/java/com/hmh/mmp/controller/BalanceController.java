package com.hmh.mmp.controller;

import com.hmh.mmp.dto.balance.BalanceDetailDTO;
import com.hmh.mmp.dto.balance.BalanceUpdateDTO;
import com.hmh.mmp.service.BalanceService;
import com.hmh.mmp.service.BankService;
import com.hmh.mmp.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/balance/*")
public class BalanceController {
    private final CashService cs;
    private final BalanceService bs;
    private final BankService bas;

    @GetMapping("{balanceId}")
    public String findById(@PathVariable("balanceId") Long balanceId, Model model) {
        System.out.println("BalanceController.findById");
        BalanceDetailDTO balanceDetailDTO = bs.findById(balanceId);
        model.addAttribute("baDTO", balanceDetailDTO);

        return "balance/findById";
    }

    @GetMapping("delete/{balanceId}")
    public String delete(@PathVariable("balanceId") Long balanceId) {
        System.out.println("BalanceController.delete");
        bs.delete(balanceId);

        return "redirect:/balance/findAll";
    }

    @GetMapping("update/{balanceId}")
    public String updateForm(@PathVariable("balanceId") Long balanceId, Model model) {
        System.out.println("BalanceController.updateForm");

        BalanceDetailDTO balanceDetailDTO = bs.findById(balanceId);
        model.addAttribute("baDTO", balanceDetailDTO);

        return "balance/update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute BalanceUpdateDTO balanceUpdateDTO) {
        System.out.println("BalanceController.update");

        Long balanceId = bs.update(balanceUpdateDTO);

        return "redirect:/balance/findById";
    }
}
