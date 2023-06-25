package com.hhg.board.repository;

import com.hhg.board.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularSearchRepository extends JpaRepository <PopularSearchEntity, String> {
}
