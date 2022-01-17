package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "card_table")
public class CardEntity extends BaseEntity {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50)
    private String cardName;
    private String cardMemo;
    private String cardPhotoName;

    private Long totalAsset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "cardEntity", fetch = FetchType.LAZY)
    private List<DebitEntity> debitEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "cardEntity", fetch = FetchType.LAZY)
    private List<CreditEntity> creditEntityList = new ArrayList<>();
}
