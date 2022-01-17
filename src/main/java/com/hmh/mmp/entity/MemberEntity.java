package com.hmh.mmp.entity;

import com.hmh.mmp.dto.MemberDetailDTO;
import com.hmh.mmp.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increments)
    @Column(name="member_id")
    private Long id;
    @Column(length = 50, unique = true)
    private String memberEmail;
    @Column(length = 30)
    private String memberPassword;
    @Column(length = 30)
    private String memberName;
    @Column(length = 30)
    private String memberNickName;
    @Column(length = 20)
    private String memberPhone;
    @Column(length = 1000)
    private String memberMemo;
    @Column
    private String memberPhotoName;


    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<BankEntity> bankEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<CashEntity> cashEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.LAZY)
    private List<CardEntity> cardEntityList = new ArrayList<>();


    // 하단 부의 내용 재설정 필요s
    public static MemberEntity saveMember(MemberSaveDTO msDTO) {
        MemberEntity mEntity = new MemberEntity();

        mEntity.setMemberEmail(msDTO.getMemberEmail());
        mEntity.setMemberPassword(msDTO.getMemberPassword());
        mEntity.setMemberName(msDTO.getMemberName());
        mEntity.setMemberNickName(msDTO.getMemberNickName());
        mEntity.setMemberPhone(msDTO.getMemberPhone());
        mEntity.setMemberMemo(msDTO.getMemberMemo());
        mEntity.setMemberPhotoName(msDTO.getMemberPhotoName());

        return mEntity;
    }

    public static MemberEntity updateMember(MemberDetailDTO memberDetailDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberEmail(memberDetailDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDetailDTO.getMemberPassword());
        memberEntity.setMemberName(memberDetailDTO.getMemberName());
        memberEntity.setMemberNickName(memberDetailDTO.getMemberNickName());
        memberEntity.setMemberPhone(memberDetailDTO.getMemberPhone());
        memberEntity.setMemberMemo(memberDetailDTO.getMemberMemo());
        memberEntity.setMemberPhotoName(memberDetailDTO.getMemberPhotoName());

        return memberEntity;
    }
}
