package com.hhg.board.repository;

import com.hhg.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> // 사용 Entity, PK 타입
{
    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);

    public UserEntity findByUserEmail(String userEmail);
}
