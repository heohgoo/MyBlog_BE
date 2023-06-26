package com.hhg.board.dto;
//token, exprTime 반환

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private String token;
    private int exprTime;
}
