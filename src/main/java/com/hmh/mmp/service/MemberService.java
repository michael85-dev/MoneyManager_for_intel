package com.hmh.mmp.service;

import com.hmh.mmp.dto.MemberDetailDTO;
import com.hmh.mmp.dto.MemberLoginDTO;
import com.hmh.mmp.dto.MemberSaveDTO;

import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO); //Jpa에서 쓰기위해서 Long으로 해야지 받을 수 있음.

    boolean login(MemberLoginDTO memberLoginDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);

    MemberDetailDTO findByEmail(String memberEmail);

    Long update(MemberDetailDTO memberDetailDTO);
}
