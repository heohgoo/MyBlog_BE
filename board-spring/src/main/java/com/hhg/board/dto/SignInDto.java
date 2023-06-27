package com.hhg.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    //필수값 지정
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
}
