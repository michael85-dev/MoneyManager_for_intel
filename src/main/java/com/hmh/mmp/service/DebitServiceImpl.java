package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.debit.DebitDetailDTO;
import com.hmh.mmp.dto.debit.DebitPagingDTO;
import com.hmh.mmp.dto.debit.DebitSaveDTO;
import com.hmh.mmp.dto.debit.DebitUpdateDTO;
import com.hmh.mmp.entity.BankEntity;
import com.hmh.mmp.entity.CardEntity;
import com.hmh.mmp.entity.DebitEntity;
import com.hmh.mmp.repository.BankRepository;
import com.hmh.mmp.repository.CardRepository;
import com.hmh.mmp.repository.DebitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebitServiceImpl implements DebitService {
    private final CardRepository crr;
    private final BankRepository br;
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

    @Override
    public Long save(DebitSaveDTO debitSaveDTO) throws IOException {
        System.out.println("DebitServiceImpl.save");
        // 파일 저장 화
        MultipartFile debitPhoto = debitSaveDTO.getDebitPhoto();
        String debitPhotoName = debitPhoto.getOriginalFilename();
        debitPhotoName = System.currentTimeMillis() + "-" + debitPhotoName;
        String savePath = "/Users/myungha/Desktop/Github/MoneyManager_for_intel/src/main/resources/photo/debit" + debitPhotoName;

        if(!debitPhoto.isEmpty()) {
            debitPhoto.transferTo(new File(savePath));
            debitSaveDTO.setDebitPhotoName(debitPhotoName);
        }

        // 사용되는 계정 정보와 카드 정보 가지고 오기.
        BankEntity bankEntity = br.findById(debitSaveDTO.getBankId()).get();
        CardEntity cardEntity = crr.findById(debitSaveDTO.getCardId()).get();

        // bank에 바로 반영하기.

        // 전체 내역 추가 하기 - 사용 관련은 기본적으로 마이너스로 추가.
        Long totalAsset = cardEntity.getTotalAsset();
        totalAsset = totalAsset - debitSaveDTO.getMinusAsset();
        cardEntity.setTotalAsset(totalAsset);
        crr.save(cardEntity);

        // 해당 데이터 저장하기 -> bankName 어 떻게 해결해야 하는지 필요함 (고민 필요) 미완
        need.
        DebitEntity debitEntity = DebitEntity.toSaveData(debitSaveDTO, cardEntity, bankEntity);
        Long debitId = dr.save(debitEntity).getId();

        return debitId;
    }

    @Override
    public DebitDetailDTO findById(Long debitId) {
        System.out.println("DebitServiceImpl.findById");

        DebitEntity debitEntity = dr.findById(debitId).get();
        DebitDetailDTO debitDetailDTO = DebitDetailDTO.toMoveData(debitEntity);

        return debitDetailDTO;
    }

    @Override
    public void delete(Long debitId) {
        System.out.println("DebitServiceImpl.delete");

        DebitEntity debitEntity = dr.findById(debitId).get();
        dr.delete(debitEntity);

    }

    @Override
    public Long update(DebitUpdateDTO debitUpdateDTO) {
        System.out.println("DebitServiceImpl.update");
        DebitEntity debitEntity = DebitEntity.toUpdateData(debitUpdateDTO);
        Long debitId = dr.save(debitEntity).getId();

        return debitId;
    }
}
