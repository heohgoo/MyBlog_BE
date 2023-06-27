package com.hhg.board.service;

import com.hhg.board.dto.ResponseDto;
import com.hhg.board.dto.SignInDto;
import com.hhg.board.dto.SignInResponseDto;
import com.hhg.board.dto.SignUpDto;
import com.hhg.board.entity.UserEntity;
import com.hhg.board.repository.UserRepository;
import com.hhg.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    public ResponseDto<?> signUp(SignUpDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        // 이메일 중복 확인
        try {
            if (userRepository.existsById(userEmail))
                return ResponseDto.setFailed("Email exists already!");
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error!");
        }

        //비밀번호가 다르다면
        if (!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("password does not matching");

        //UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);
        //UserRepository를 통해 데이터베이스에 저장
        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error!");
        }


        return ResponseDto.setSuccess("SignUp Success!", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        try {
            boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
            if (!existed) return ResponseDto.setFailed("SignUp information does not exist");
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error!");
        }

        UserEntity userEntity = null;
        try{
            userEntity = userRepository.findById(userEmail).get();
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error!");
        }
        userEntity.setUserPassword("");

        //JWT(Json Web Token)
        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);

        return ResponseDto.setSuccess("SignIn Success", signInResponseDto);
    }
}
