package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.cash.CashDetailDTO;
import com.hmh.mmp.dto.cash.CashPagingDTO;
import com.hmh.mmp.dto.cash.CashSaveDTO;
import com.hmh.mmp.entity.CashEntity;
import com.hmh.mmp.entity.MemberEntity;
import com.hmh.mmp.repository.CashRepository;
import com.hmh.mmp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService{
    private final CashRepository csr;
    private final MemberRepository mr;

    @Override
    public Long save(CashSaveDTO cashSaveDTO) throws IOException {
        Optional<MemberEntity> memberEntityOptional = mr.findById(cashSaveDTO.getMemberId());
        MemberEntity memberEntity = memberEntityOptional.get();

        MultipartFile cashPhoto = cashSaveDTO.getCashPhoto();
        String cashPhotoName = cashPhoto.getOriginalFilename();
        cashPhotoName = System.currentTimeMillis() + "-" + cashPhotoName;

        String savePath = "/Users/myungha/Desktop/Github/MoneyManager_for_intel/src/main/resources/photo/cash" + cashPhotoName;

        if (!cashPhoto.isEmpty()) {
            cashPhoto.transferTo(new File(savePath));
            cashSaveDTO.setCashPhotoName(cashPhotoName);
        }

        CashEntity cashEntity = CashEntity.toSaveCash(cashSaveDTO, memberEntity);

        CashEntity cashSave = csr.save(cashEntity);

        Long cashId = cashSave.getId();

        return cashId;
    }

    @Override
    public List<CashDetailDTO> findAll(Long memberId) {
        List<CashEntity> cashEntityList = csr.findAll(memberId); // 이게 가능한가? 동일한 memberId가 꽤 있을텐데.
        List<CashDetailDTO> cashDetailDTOList = new ArrayList<>();

        for (CashEntity ce: cashEntityList) {
            cashDetailDTOList.add(CashDetailDTO.toMoveData(ce));
        }

        return cashDetailDTOList;
    }

    @Override
    public Page<CashPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 총 페이지 넘버를 가지고 옴.

        page = (page == 1) ? 0 : (page - 1); // page가 1이면 0으로 표시하고 아니면 페이지값 -1로 표시 (그냥 page -1)로 해도 무방함

        Page<CashEntity> cashEntityPage = csr.findAll(PageRequest.of(page, PagingConst.CS_PAGE_LIMIT))

        return null;
    }
}
