package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "card_table")
public class CardEntity {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    @NotBlank
    @Column(length = 50)
    private String cardName;
    private String cardMemo;
    private String cardPhotoName;
}
