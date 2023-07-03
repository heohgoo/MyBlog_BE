package com.hhg.board.controller;

import com.hhg.board.dto.ResponseDto;
import com.hhg.board.entity.BoardEntity;
import com.hhg.board.entity.PopularSearchEntity;
import com.hhg.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
//    @GetMapping("/")
//    public String getBoard(@AuthenticationPrincipal String userEmail) {
//        return userEmail + " 이메일입니다.";
//    }

    @Autowired
    BoardService boardService;
    @GetMapping("/top3")
    public ResponseDto<List<BoardEntity>> getTop3() {
        return boardService.getTop3();
    }

    @GetMapping("/list")
    public ResponseDto<List<BoardEntity>> getList() {
        return boardService.getList();
    }

    @GetMapping("/popularsearchList")
    public ResponseDto<List<PopularSearchEntity>> getPopularSearch() {
        return boardService.getPopularSearchList();
    }

    @GetMapping("/search/{boardTitle}")
    public ResponseDto<List<BoardEntity>> getSearchList(@PathVariable("boardTitle") String title) {
        return null;
    }

}
