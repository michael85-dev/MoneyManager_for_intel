package com.hmh.mmp.entity;

import com.hmh.mmp.entity.list.CardFirstListEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "debit_entity")
public class DebitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity cardEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bankEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "debitEntity")
    private List<CardFirstListEntity> cardFirstListEntityList = new ArrayList<>();

    private LocalDate calDate;
    private LocalDateTime calTime;
    private String debitName; //내역명
    private String debitPhotoName; // 영수증
    private Long minusAsset; // 지출
    private String debitMemo; // 내역
    private Double debitGet; // 할인
    private Double debitPercent; // 할인율
}
