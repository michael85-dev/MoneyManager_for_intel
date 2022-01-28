package com.hmh.mmp.service;

import com.hmh.mmp.common.PagingConst;
import com.hmh.mmp.dto.balance.BalanceDetailDTO;
import com.hmh.mmp.dto.balance.BalancePagingDTO;
import com.hmh.mmp.entity.BalanceEntity;
import com.hmh.mmp.entity.CashEntity;
import com.hmh.mmp.repository.BalanceRepository;
import com.hmh.mmp.repository.CashRepository;
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
public class BalanceServiceImpl implements BalanceService{
    private final BalanceRepository bar;
    private final CashRepository csr;

    @Override
    public List<BalanceDetailDTO> findAll(Long cashId) {
        Optional<CashEntity> cashEntityOptional = csr.findById(cashId);
        CashEntity cashEntity = cashEntityOptional.get();

        List<BalanceEntity> balanceEntityList = cashEntity.getBalanceEntityList();

//        List<BalanceEntity> balanceEntityList = bar.findAll(cashId);
        List<BalanceDetailDTO> balanceDetailDTOList = new ArrayList<>();

        for (BalanceEntity be: balanceEntityList) {
            balanceDetailDTOList.add(BalanceDetailDTO.toMoveData(be));
        }

        return balanceDetailDTOList;
    }

    @Override
    public Page<BalancePagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);

        Page<BalanceEntity> balanceEntities = bar.findAll(PageRequest.of(page, PagingConst.BA_PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BalancePagingDTO> balancePage = balanceEntities.map(
                balance -> new BalancePagingDTO(balance.getId(),
                        balance.getBalanceName(),
                        balance.getBalanceMemo(),
                        balance.getBalancePhotoName(),
                        balance.getMinusAsset(),
                        balance.getPlusAsset(),
                        balance.getNowTime())
        );

        return balancePage;
    }
}
