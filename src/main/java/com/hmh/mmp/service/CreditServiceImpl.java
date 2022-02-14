package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.card.CardDetailDTO;
import com.hmh.mmp.dto.credit.CreditDetailDTO;
import com.hmh.mmp.dto.credit.CreditPagingDTO;
import com.hmh.mmp.dto.credit.CreditSaveDTO;
import com.hmh.mmp.dto.credit.CreditUpdateDTO;
import com.hmh.mmp.entity.CardEntity;
import com.hmh.mmp.entity.CreditEntity;
import com.hmh.mmp.repository.CardRepository;
import com.hmh.mmp.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CardRepository crr;
    private final CreditRepository cdr;

    @Override
    public Page<CreditPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);

        Page<CreditEntity> creditEntities = cdr.findAll(PageRequest.of(page, PagingConst.CR_PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<CreditPagingDTO> creditPage = creditEntities.map(
                credit -> new CreditPagingDTO(credit.getId(),
                        credit.getCreditGet(),
                        credit.getCreditMemo(),
                        credit.getCreditName(),
                        credit.getCreditPhotoName(),
                        credit.getBankEntity(),
                        credit.getMinusAsset(),
                        credit.getDate(),
                        credit.getMonth(),
                        credit.getRate())
        );

        return creditPage;
    }

    @Override
    public List<CreditDetailDTO> findAll(Long cardId) {
        Optional<CardEntity> cardEntityOptional = crr.findById(cardId);
        CardEntity cardEntity = cardEntityOptional.get();
        List<CreditEntity> creditEntities = cardEntity.getCreditEntityList();

        List<CreditDetailDTO> creditDetailDTOList = new ArrayList<>();

        for (CreditEntity ce: creditEntities) {
            creditDetailDTOList.add(CreditDetailDTO.toMoveData(ce));
        }

        return creditDetailDTOList;
    }

    @Override
    public Long save(CreditSaveDTO creditSaveDTO) {
        System.out.println("CreditServiceImpl.save");


        CardEntity cardEntity = crr.findById(creditSaveDTO.getCardId()).get();
        CreditEntity creditEntity = CreditEntity.toSaveData(creditSaveDTO, cardEntity);
        Long creditId = cdr.save(creditEntity).getId();
        return creditId;
    }

    @Override
    public CreditDetailDTO findById(Long creditId) {
        System.out.println("CreditServiceImpl.findById");
        CreditEntity creditEntity = cdr.findById(creditId).get();
        CreditDetailDTO creditDetailDTO = CreditDetailDTO.toMoveData(creditEntity);

        return creditDetailDTO;
    }

    @Override
    public void delete(Long creditId) {
        System.out.println("CreditServiceImpl.delete");
        CreditEntity creditEntity = cdr.findById(creditId).get();
        cdr.delete(creditEntity);
    }

    @Override
    public Long update(CreditUpdateDTO creditUpdateDTO) {
        System.out.println("CreditServiceImpl.update");
        CreditEntity creditEntity = cdr.findById(creditUpdateDTO.getCreditId()).get();
        CardEntity cardEntity = crr.findById(creditUpdateDTO.getCardId()).get();
        Long minusAsset = creditEntity.getMinusAsset();

        // 할부 이자 계산 필요

        // 할부 분활하여 적용하는 방법 필요.


        // 자산 처리..
        Long totalAsset = cardEntity.getTotalAsset();
        totalAsset = totalAsset - minusAsset;
        cardEntity.setTotalAsset(totalAsset);
        crr.save(cardEntity);

        // 데이터 이관
        creditEntity = CreditEntity.toUpdateData(creditUpdateDTO);
        Long creditId = cdr.save(creditEntity).getId();
        return creditId;
    }
}
