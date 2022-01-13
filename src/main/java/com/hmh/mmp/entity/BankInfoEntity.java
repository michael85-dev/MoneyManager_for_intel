package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bankinfo_table")
public class BankInfoEntity {
    @Id
    @Column(name = "bankinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BankEntity bankId;
    @Column
    private String bankInfo;
    @Column
    private String InfoMemo;
    @Column
    private String bankInfoPhotoName;

    private Long plusAsset;
    private Long minusAsset;
}
