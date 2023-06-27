package com.hhg.board.dto;
//token, exprTime, user 데이터 반환
import com.hhg.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private String token;
    private int exprTime;
    private UserEntity user;
}
