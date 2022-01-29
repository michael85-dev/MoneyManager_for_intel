package com.hmh.mmp.entity;

import com.hmh.mmp.entity.list.FirstListEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 현금 관련 내역을 보기 위한 것.
@Entity
@Getter
@Setter
@Table(name = "balance_table")
public class BalanceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cash_id")
    private CashEntity cashEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "balanceEntity")
    private List<FirstListEntity> firstListEntityList = new ArrayList<>();

    private LocalDate date;
    private String balanceMemo;
    private String balanceName;
    private long plusAsset;
    private long minusAsset;

    private String balancePhotoName;


}
