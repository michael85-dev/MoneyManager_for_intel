package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "cash_table")
public class CashEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    private String cashName;
    private String cashMemo;

    private String cashPhotoName;
    private Long totalAsset;
}
