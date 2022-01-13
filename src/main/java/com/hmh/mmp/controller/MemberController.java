package com.hmh.mmp.controller;

import com.hmh.mmp.dto.MemberDetailDTO;
import com.hmh.mmp.dto.MemberLoginDTO;
import com.hmh.mmp.dto.MemberSaveDTO;
import com.hmh.mmp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.hmh.mmp.common.SessionConst.*;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor // final이 붙어있는ㄱ ㅓㅅ에 대한 생성자를 만들어줌?
public class MemberController {
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("msave", new MemberSaveDTO()); // new MemberSaveDTO() 는 생성자임.

        return "member/save";
    }

    @PostMapping("save")
    public String save(@PathVariable @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult br) throws IOException {
        // @ModelAttribute의 member는 대체 어디서 넘어온 것인가.
        if (br.hasErrors()) {
            return "member/save";
        }

        // 이메일 중복 처리
        try {
            ms.save(memberSaveDTO);
        } catch (IllegalStateException e) {
            br.reject("eCheck", e.getMessage());
        }

        return "index";
    }

    @GetMapping("login")
    public String loginForm(Model model) {
        model.addAttribute("mlogin", new MemberSaveDTO());

        return "member/login";
    }

    @PostMapping("login")
    public String login(@PathVariable @ModelAttribute("member") MemberLoginDTO memberLoginDTO, BindingResult br, HttpSession session) {
        boolean checkResult = ms.login(memberLoginDTO); // MemberLoginDTO에 다가 Entity데이터를 담아서 비교?

        if (checkResult) {
            session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
            // 해당 loginEmail의 값을 SessionConst라는 폴더를 만들어서 적용도 가능함.
            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());
            session.setAttribute("loginId", memberLoginDTO.getId());
            session.setAttribute(LOGIN_ID, memberLoginDTO.getId());
            session.setAttribute("loginNickName", memberLoginDTO.getMemberNickName());
            session.setAttribute(NICK_NAME, memberLoginDTO.getMemberNickName());

            return "main";
        } else {
            return "member/login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "index";
    }

    @GetMapping
    public String findAll(Model model) {
        List<MemberDetailDTO> memberDetailDTO = ms.findAll();
        model.addAttribute("memberList", memberDetailDTO);

        return "member/findAll";
    }

    @PostMapping("{memberId}")
    public @ResponseBody MemberDetailDTO findById(@PathVariable("memberId") Long memberId) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);

        return memberDetailDTO;
    }

    // 로그인 한 사람이 자기 정보 조회
    @GetMapping("detail")
    public String detail(Model model, HttpSession session) {
        String memberEmail = (String) session.getAttribute("loginEmail");

        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberEmail);
        model.addAttribute("mdDTO", memberDetailDTO);

        return "member/detail";
    }

    @DeleteMapping("{memberId}")
    public ResponseEntity delete(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("update")
    public String updatePost(@ModelAttribute MemberDetailDTO memberDetailDTO) {
        Long memberId = ms.update(memberDetailDTO);

        return "redirect:/member/" + memberDetailDTO.getMemberId();
    }

    @GetMapping("select")
    public String select(Model model, HttpSession session, MemberLoginDTO memberLoginDTO) {
        Long memberId = memberLoginDTO.getId();
        ms.findAll();

//        bs.findAll(memberId); // 일시 정지

        return "/select";
    }
}
