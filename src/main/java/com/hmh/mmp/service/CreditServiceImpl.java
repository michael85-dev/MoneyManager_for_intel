package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.credit.CreditDetailDTO;
import com.hmh.mmp.dto.credit.CreditPagingDTO;
import com.hmh.mmp.dto.credit.CreditSaveDTO;
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
}
