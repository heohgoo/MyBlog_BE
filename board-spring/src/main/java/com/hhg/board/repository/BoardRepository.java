package com.hhg.board.repository;

import com.hhg.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository <BoardEntity, Integer> {
}
