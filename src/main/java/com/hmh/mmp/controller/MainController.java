package com.hmh.mmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.hmh.mmp.common.SessionConst.LOGIN_ID;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {

        return "index";
    }
    @GetMapping("select")
    public String createForm(HttpSession session, Model model) {
//        String memberEmail = (String)session.getAttribute("loginEmail");
//
//        // DetailDTO 만들기 전에 중지. (Save로 해도 되지만 해당 session에서 관련 계정의 데이터를 가져와야지 무분별하게 전체 데이터에서 가지고 오면 안디니까....
        Long memberId = (Long) session.getAttribute(LOGIN_ID);

        return "create";
    }
}
