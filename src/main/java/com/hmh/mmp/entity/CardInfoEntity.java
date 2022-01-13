package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cardinfo_table")
public class CardInfoEntity {
    @Id
    @Column(name = "cardInfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity cardId;

    private String cardInfoName;
    private String cardInfoMemo;
    private String cardInfoPhotoName;
    private Long plusAsset;
    private Long minusAsset;

}
