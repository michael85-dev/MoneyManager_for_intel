package com.hmh.mmp.controller;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.*;
import com.hmh.mmp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final BankService bs;
    private final BoardService bos;
    private final CardService crs;
    private final CashService css;

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
    public String login(@PathVariable @ModelAttribute("member") MemberLoginDTO memberLoginDTO, BindingResult br, HttpSession session, Model model, Pageable pageable) {
        boolean checkResult = ms.login(memberLoginDTO); // MemberLoginDTO에 다가 Entity데이터를 담아서 비교?

        if (checkResult) {
            session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
            // 해당 loginEmail의 값을 SessionConst라는 폴더를 만들어서 적용도 가능함.
            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());
            session.setAttribute("loginId", memberLoginDTO.getId());
            session.setAttribute(LOGIN_ID, memberLoginDTO.getId());
            session.setAttribute("loginNickName", memberLoginDTO.getMemberNickName());
            session.setAttribute(NICK_NAME, memberLoginDTO.getMemberNickName());

            // 여기서 페이징 부터 해서 모든 데이터를 메인에 뿌려 줘야함.
            // 은행 관련
            List<BankDetailDTO> bankList = bs.findAll(memberLoginDTO.getId());
            model.addAttribute("bList", bankList);

            Page<BankPagingDTO> bPageList = bs.paging(pageable);
            model.addAttribute("bpList", bPageList);
                // 해당 내용 메서드로 만들지 고민....
            int bank_startPage = (((int) (Math.ceil((double)pageable.getPageNumber() / PagingConst.B_BLOCK_LIMIT))) - 1) * PagingConst.B_BLOCK_LIMIT + 1;
            int bank_endPage = ((bank_startPage + PagingConst.B_BLOCK_LIMIT - 1) < bPageList.getTotalPages()) ? bank_startPage + PagingConst.B_BLOCK_LIMIT - 1 : bPageList.getTotalPages();
            model.addAttribute("bank_startPage", bank_startPage);
            model.addAttribute("bank_endPage", bank_endPage);

            // 카드 관련
            List<CardDetailDTO> cardList = crs.findAll(memberLoginDTO.getId());
            model.addAttribute("crList", cardList);

            // 현금 관련
            List<CashDetailDTO> cashList = css.findAll(memberLoginDTO.getId());
            model.addAttribute("csList", cashList);

            // 게시판 관련

            // 공지사항 관련

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
    public String findAll(Model model, @PageableDefault(page = 1) Pageable pageable) {
        List<MemberDetailDTO> memberDetailDTO = ms.findAll();
        model.addAttribute("memberList", memberDetailDTO);

        // 페이지 작업 하기.
        Page<MemberPagingDTO> mPageList = ms.paging(pageable);
        model.addAttribute("pList", mPageList);

        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < mPageList.getTotalPages()) ? startPage * PagingConst.BLOCK_LIMIT - 1 : mPageList.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "member/findAll";
    }

    @PostMapping("{memberId}") // ajax로 받음.
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
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("mList", memberList);

        List<BankDetailDTO> bankList = bs.findAll(memberLoginDTO.getId());// 해당 관련 memberId 관련 넣어줘야함.
        model.addAttribute("bList", bankList);



//        bs.findAll(memberId); // 일시 정지

        return "/select";
    }
}
