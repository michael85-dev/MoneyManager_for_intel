package com.hmh.mmp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 카드의 신용카드 관련 정보를 저장하기 위한 것
@Entity
@Getter
@Setter
@Table(name = "credit_table")
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity cardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    private String creditName; // 내역
    private String creditPhotoName; // 사진명
    private String creditMemo;
    private Long minusAsset; // 지출
    private Double rate; // 할부이자.
    private Integer month; // 할부
    private Double creditGet; // 캐쉬백

}
