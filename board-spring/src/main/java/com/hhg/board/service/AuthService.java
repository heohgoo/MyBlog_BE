package com.hhg.board.service;

import com.hhg.board.dto.ResponseDto;
import com.hhg.board.dto.SignInDto;
import com.hhg.board.dto.SignInResponseDto;
import com.hhg.board.dto.SignUpDto;
import com.hhg.board.entity.UserEntity;
import com.hhg.board.repository.UserRepository;
import com.hhg.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userPassword);
        userEntity.setUserPassword(encodedPassword);

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

        UserEntity userEntity = null;
        try{
            userEntity = userRepository.findByUserEmail(userEmail);
            //잘못된 이메일
            if (userEntity == null) return ResponseDto.setFailed("Sign In Failed");
            //잘못된 패스워드
            if (!passwordEncoder.matches(userPassword, userEntity.getUserPassword()))
                return ResponseDto.setFailed("Sign In Failed");
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
