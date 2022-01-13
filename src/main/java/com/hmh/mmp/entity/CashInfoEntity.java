package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "cashinfo_table")
public class CashInfoEntity {
    @Id
    @Column(name = "cashinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cash_id")
    private CashEntity cashId;

    @Column(length = 40)
    private String cashInfo;
    @Column(length = 2000)
    private String infoMemo;

    private Long plusAsset;
    private Long minusAsset;

    @Column
    private String infoPhotoName;
}