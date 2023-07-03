package com.hhg.board.repository;

import com.hhg.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {

    public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(Date boardWriteDate);
    //좋아요 수가 많은 3개의 게시물을 게시 시간 순서대로 가져온다.

    public List<BoardEntity> findByOrderByBoardWriteDateDesc();
    //최신 순서대로 게시물 가져온다.

}
