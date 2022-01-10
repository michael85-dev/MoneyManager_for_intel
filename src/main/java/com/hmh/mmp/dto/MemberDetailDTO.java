package com.hmh.mmp.dto;

import com.hmh.mmp.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {
    private Long memberId;
    @NotBlank(message="이메일은 필수입니다.")
    private String memberEmail;
    @NotBlank
    @Length(min=4, max=16, message="4~16자리로 입력하세요")
    private String memberPassword;
    @NotBlank(message="이름은 필수 입니다.")
    private String memberName;
    private String memberPhone;
    private String memberMemo;
    private MultipartFile memberPhoto;
    private String memberPhotoName;

    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        // MemberEntity에 있는 값들을 이용해서 toMemberDetailDTO를 정의한다.
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        memberDetailDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDetailDTO.setMemberMemo(memberEntity.getMemberMemo());
        memberDetailDTO.setMemberPhotoName(memberEntity.getMemberPhotoName());

        return memberDetailDTO;
    }
}
