package com.hmh.mmp.entity;

import com.hmh.mmp.dto.BankDetailDTO;
import com.hmh.mmp.dto.BankSaveDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="bank_table")
public class BankEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Long memberId;
//    // Fk 관계를 주어야 하는데 .?
//    @ManyToOne
//    private

    @Column(length = 30, unique = true)
    private String bankName;

    @Column(length = 1000)
    private String bankMemo;

    private Long totalAsset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "bankEntity", fetch = FetchType.LAZY)
    private List<AccountEntity> accountEntityList= new ArrayList<>();

    // BankEntity에다가 Save와 Update등의 내용을 담아야함....
    public static BankEntity saveBank(BankSaveDTO bankSaveDTO, MemberEntity memberEntity) {
        BankEntity bankEntity = new BankEntity();
//        bankEntity.setId(session.getId()); // 어떻게 가지고 와야할까...
//        bankEntity.setMemberId(bankSaveDTO.getMemberId());
//        bankEntity.getMemberId().getId(bankSaveDTO.getMemberId()); // 확인 필요
        // 해당 값을 넣어주지 않아도 알아서 들어가는 것인지에 대해서 확인할 필요가 있음.
        bankEntity.setMemberEntity(memberEntity);
        bankEntity.setBankName(bankSaveDTO.getBankName());
        bankEntity.setBankMemo(bankSaveDTO.getBankMemo());
        bankEntity.setTotalAsset(bankSaveDTO.getTotalAsset());

        return bankEntity;
    }

    public static BankEntity updateBank(BankDetailDTO bankDetailDTO, MemberEntity memberEntity) {
        BankEntity bankEntity = new BankEntity();

        bankEntity.setId(bankDetailDTO.getBankId());
        bankEntity.setMemberEntity(memberEntity);
//        bankEntity.setMemberId(bankDetailDTO.getMemberId()); // 동일한 문제.
        bankEntity.setBankName(bankDetailDTO.getBankName());
        bankEntity.setBankMemo(bankDetailDTO.getBankMemo());
        bankEntity.setTotalAsset(bankDetailDTO.getTotalAsset());

        return bankEntity;
    }
}
