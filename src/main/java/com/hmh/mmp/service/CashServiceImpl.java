package com.hmh.mmp.service;

import com.hmh.mmp.dto.CashSaveDTO;
import com.hmh.mmp.entity.CashEntity;
import com.hmh.mmp.entity.MemberEntity;
import com.hmh.mmp.repository.CashRepository;
import com.hmh.mmp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService{
    private final CashRepository csr;
    private final MemberRepository mr;

    @Override
    public Long save(CashSaveDTO cashSaveDTO) {
        Optional<MemberEntity> memberEntityOptional = mr.findById(cashSaveDTO.getMemberId());
        MemberEntity memberEntity = memberEntityOptional.get();

        CashEntity cashEntity = CashEntity.toSaveCash(cashSaveDTO, memberEntity);

        CashEntity cashSave = csr.save(cashEntity);

        Long cashId = cashSave.getId();

        return cashId;
    }
}
