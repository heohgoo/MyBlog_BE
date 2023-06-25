package com.hhg.board.repository;

import com.hhg.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository <CommentEntity, Integer> {
}
