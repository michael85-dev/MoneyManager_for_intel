package com.hmh.mmp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 회원제 게시판 관련을 넣어두기.
@Entity
@Setter
@Getter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String boardWriter;
    private String boardContents;
    private String boardTitle;
    private String boardPassword;
    private Integer boardHits;
    private String boardPhotoName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


}
