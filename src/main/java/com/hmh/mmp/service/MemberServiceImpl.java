package com.hmh.mmp.service;

import com.hmh.mmp.dto.MemberDetailDTO;
import com.hmh.mmp.dto.MemberLoginDTO;
import com.hmh.mmp.dto.MemberSaveDTO;
import com.hmh.mmp.entity.MemberEntity;
import com.hmh.mmp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        /*
            1. MemberSaveDTO -> MemberEntity에 옮기기 : msDTO는 더 이상 다음단계에서는 쓰지 않음. 해당 데이터는 Entity에서 다룸
                - 여기서는 MemberEntity의 saveMember 메서드
            2. MemberRepository의 save 메서드 호출하면서 MemberEntity 객체 전달

         */
        MemberEntity mEntity = MemberEntity.saveMember(memberSaveDTO); // 이것을 통해 가입 진행됨

        // 이메일 체크
        MemberEntity emailCheck = mr.findByIdMemberEmail(memberSaveDTO.getMemberEmail()); // 지금 설정하는게 아님...

        if (emailCheck != null) {
            throw new IllegalStateException("중복된 이메일입니다.");
        } else {
            return mr.save(mEntity).getId();
        }

    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        // MemberEntity -> MemberLogin
        MemberEntity memberEntity = mr.findByIdMemberEmail(memberLoginDTO.getMemberEmail()); // 왜냐면 로그인에서 쓴 데이터는 memberLogin에서 받았기 때문에.

        if (memberEntity != null) {
            if (memberLoginDTO.getMemberPassword().equals(memberEntity.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntity = mr.findAll(); // Jpa의 findAll이란 명령어를 통해서 모든 데이터를 MemberEntity에 담는다.
        List<MemberDetailDTO> memberDetailDTOList = new ArrayList<>();
        for (MemberEntity m : memberEntity) {
            memberDetailDTOList.add(MemberDetailDTO.toMemberDetailDTO(m)); // MemberDetailDTO 에 static 정의해준 메서드에 해당 entity의 데이터 담는다.
        }

        return memberDetailDTOList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        // Entity(Repo)로 부터 데이터 를 가지고 와야함.
        Optional<MemberEntity> member = mr.findById(memberId); // 데이터 찾아서 가지고 옴

        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(member.get());

        return memberDetailDTO;
//        return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
    }

    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

    // session의 데이터를 가지고 모든 데이터 또는 부분 데이터를 가지고 오기 위한 메서드.
    @Override
    public MemberDetailDTO findByEmail(String memberEmail) {
        MemberEntity memberEntity = mr.findByIdMemberEmail(memberEmail);
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);

        return memberDetailDTO;
    }

    @Override
    public Long update(MemberDetailDTO memberDetailDTO) {
        MemberEntity memberEntity = MemberEntity.updateMember(memberDetailDTO);
        Long memberId = mr.save(memberEntity).getId(); // controller에서 페이지 호출을 하기 위해서 필요...
        return memberId;
    }
}
