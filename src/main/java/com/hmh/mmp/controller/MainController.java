package com.hmh.mmp.controller;

import com.hmh.mmp.dto.account.AccountDetailDTO;
import com.hmh.mmp.dto.balance.BalanceDetailDTO;
import com.hmh.mmp.dto.bank.BankDetailDTO;
import com.hmh.mmp.dto.board.BoardDetailDTO;
import com.hmh.mmp.dto.card.CardDetailDTO;
import com.hmh.mmp.dto.cash.CashDetailDTO;
import com.hmh.mmp.dto.credit.CreditDetailDTO;
import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.member.MemberDetailDTO;
import com.hmh.mmp.dto.notice.NoticeDetailDTO;
import com.hmh.mmp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.hmh.mmp.common.SessionConst.LOGIN_ID;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService ms;
    private final BankService bs;
    private final AccountService as;
    private final CashService css;
    private final BalanceService bas;
    private final CardService crs;
    private final DebitService dbs;
    private final CreditService cds;
    private final BoardService bos;
    private final NoticeService ns;

    @GetMapping("/") // 이걸 주소를 이대로 가져가야 하는지 아니면 ()를 지우고 아무것도 없게 해야하는지?
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

    @GetMapping("/main") // 주소 관련해서 정리가 필요할 것으로 생각됨. (Login에다가 넣어두기에는 약간 애매한 느낌인데.)
    public String findAll(Model model, HttpSession session) {
        System.out.println("MainController.findAll + 모든 데이터를 가지고 오는 것.");
        Long memberId = (Long)session.getAttribute(LOGIN_ID);
        // 회원 정보 불러오기
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        // 해당 회원의 card등 모든 정보 가지고 와야할 필요가 있음. -> 근데 memberId로는 여러개가 검색이 되어서 제대로 대응이 안될 테니 해당 부분에 대해서 상세 부분을 검색해 올 필요가 있음.
        List<CardDetailDTO> cardDetailDTOList = crs.findAll(memberId);

        // cash 관련 정보를 불러와야 함
        List<CashDetailDTO> cashDetailDTOList = css.findAll(memberId);

        // bank 관련 정보 불러오기
        List<BankDetailDTO> bankDetailDTOList = bs.findAll(memberId);

        // board 관련 정보 불러오기
        List<BoardDetailDTO> boardDetailDTOList = bos.findAll();

        // Notice 관련 정보 불러오기
        List<NoticeDetailDTO> noticeDetailDTOList = ns.findAll();

        // account 관련 정보 불러오기
        // bankId로 하면 한개의 bankId만을 불러올 것이고 accountId는 해당 되지 않음. 때문에 memberId를 통해서 불러와야하는데. memberId의 경우 다양한 배열이 존재할 것이기 때문에 해당 데이터를 어떻게 불러와야 할지 고안이 필요함.
        List<AccountDetailDTO> accountDetailDTOList = as.findData(memberId);

        // credit 관련 정보 불러오기
        List<CreditDetailDTO> creditDetailDTOList = cds.findData(memberId);

        // debit 관련 정보 불러오기
        List<DebitDetailDTO> debitDetailDTOList = dbs.findData(memberId);

        // balance 관련 정보 불러오기
        List<BalanceDetailDTO> balanceDetailDTOList = bas.findData(memberId);

        return "redirect:/main"; // 왜 주소에서 오류가 발생할 까?
    }

    @GetMapping("graph")
    public String stickGraph(Model model) {

        return "main";
    }
}
