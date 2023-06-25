package com.hhg.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Board")
@Table(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동 생성 어노테이션
    //IDENTITY => AUTO_INC, SEQUENCE => Oracle,Postgre sequence 지원,
    //TABLE => 키 생성 전용 테이블 생성 후 이름,값 만들고 시퀀스 흉내
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImage;
    private String boardVideo;
    private String boardFile;
    private String boardWriterEmail;
    private String boardWriterProfile;
    private String boardWriterNickname;
    private String boardWriteDate;
    private int boardClickCount;
    private int boardLikeCount;
    private int boardCommentCount;
}
