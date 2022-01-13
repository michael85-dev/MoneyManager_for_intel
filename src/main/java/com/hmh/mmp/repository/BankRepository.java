package com.hmh.mmp.repository;

import com.hmh.mmp.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<BankEntity, Long> {
    List<BankEntity> findAll(Long memberId);
}
