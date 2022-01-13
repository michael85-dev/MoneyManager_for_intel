package com.hmh.mmp.service;

import com.hmh.mmp.dto.BankDetailDTO;
import com.hmh.mmp.dto.BankSaveDTO;
import com.hmh.mmp.dto.MemberDetailDTO;
import com.hmh.mmp.entity.BankEntity;
import com.hmh.mmp.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImp implements BankService{
    private final BankRepository br;

    @Override
    public Long save(BankSaveDTO bankSaveDTO) {

        BankEntity bankEntity = BankEntity.saveBank(bankSaveDTO);

        System.out.println("BankEntity.MemberId : " + bankEntity.getId());

        bankSaveDTO.setMemberId(bankEntity.getId()); // 로그인 중인 고객의 정보 가지고 오기

        bankEntity.getId();
        return br.save(bankEntity).getId(); //이 Id는 어디서 나온 것일까..... // 현재 여기 까지 진행...
    }

    @Override
    public List<BankDetailDTO> findAll(Long memberId) {
        List<BankEntity> bankEntityList = br.findAll(memberId);
        List<BankDetailDTO> bankDetailDTOList = new ArrayList<>();

        for (BankEntity b: bankEntityList) {
//            bankDetailDTOList.add(bankEntityList.get(b));
            // 해당 메서드에 대해서 toBankDetail 관련 메서드 필요.
            bankDetailDTOList.add(BankDetailDTO.toBankDetail(b));
        }
        return bankDetailDTOList;
    }
}
