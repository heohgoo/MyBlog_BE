package com.hhg.board.controller;

import com.hhg.board.dto.ResponseDto;
import com.hhg.board.dto.SignUpDto;
import com.hhg.board.dto.SignUpResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/signUp")
    public ResponseDto<SignUpResponseDto> signUp(@RequestBody SignUpDto requestBody) {
        System.out.println(requestBody.toString());
        return null;
    }
}
