package com.hhg.board.repository;

import com.hhg.board.entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository <LikesEntity, Integer> {
}
