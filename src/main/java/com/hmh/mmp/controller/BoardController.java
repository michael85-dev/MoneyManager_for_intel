package com.hmh.mmp.controller;

import com.hmh.mmp.dto.BoardDetailDTO;
import com.hmh.mmp.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bos;

    public String findAll(Model model) {
        List<BoardDetailDTO> boardDetailDTOList = bos.findAll();

        model.addAttribute("bdList", boardDetailDTOList);



        return "board/findAll";
    }
}
