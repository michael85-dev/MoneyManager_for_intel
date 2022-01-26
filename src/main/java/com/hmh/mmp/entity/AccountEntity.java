package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

// 은행 관련 내역을 보기 위한 것.
@Entity
@Getter
@Setter
@Table(name = "account_table")
public class AccountEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    private LocalDate date;
    private Long plusAsset;
    private Long minusAsset;
    private String accountName;
    private String accountPhotoName;
    private String accountMemo;
}
