package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.debit.DebitPagingDTO;
import com.hmh.mmp.entity.CardEntity;
import com.hmh.mmp.entity.DebitEntity;
import com.hmh.mmp.repository.CardRepository;
import com.hmh.mmp.repository.DebitRepository;
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
public class DebitServiceImpl implements DebitService {
    private final CardRepository crr;
    private final DebitRepository dr;

    @Override
    public Page<DebitPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();

        page = (page == 1) ? 0 : (page - 1);
        Page<DebitEntity> debitEntities = dr.findAll(PageRequest.of(page, PagingConst.DR_PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id'")));
        Page<DebitPagingDTO> debitPage = debitEntities.map(
                debit -> new DebitPagingDTO(debit.getId(),
                        debit.getDebitGet(),
                        debit.getDebitMemo(),
                        debit.getDebitName(),
                        debit.getMinusAsset(),
                        debit.getBankEntity(),
                        debit.getDebitPhotoName(),
                        debit.getDate())
        );

        return debitPage;
    }

    @Override
    public List<DebitDetailDTO> findAll(Long cardId) {
        Optional<CardEntity> cardEntityOptional = crr.findById(cardId);
        CardEntity cardEntity = cardEntityOptional.get();

        List<DebitEntity> debitEntities = cardEntity.getDebitEntityList();

        //        List<DebitEntity> debitEntities = dr.findAll(cardId);
        List<DebitDetailDTO> debitDetailDTOList = new ArrayList<>();

        for (DebitEntity de: debitEntities) {
            debitDetailDTOList.add(DebitDetailDTO.toMoveData(de));
        }

        return debitDetailDTOList;
    }
}
