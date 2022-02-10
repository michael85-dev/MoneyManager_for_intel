package com.hmh.mmp.controller;

import com.hmh.mmp.dto.balance.BalanceDetailDTO;
import com.hmh.mmp.service.BalanceService;
import com.hmh.mmp.service.BankService;
import com.hmh.mmp.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
