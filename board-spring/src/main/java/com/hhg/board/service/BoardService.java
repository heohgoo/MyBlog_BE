package com.hhg.board.service;

import com.hhg.board.dto.ResponseDto;
import com.hhg.board.entity.BoardEntity;
import com.hhg.board.entity.PopularSearchEntity;
import com.hhg.board.repository.BoardRepository;
import com.hhg.board.repository.PopularSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    PopularSearchRepository popularSearchRepository;

    public ResponseDto<List<BoardEntity>> getTop3() {
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        //List => interface, 상속받는 ArrayList객체 이용
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        //7일 전까지
        try {
            boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<BoardEntity>> getList() {
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList = boardRepository.findByOrderByBoardWriteDateDesc();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }


    public ResponseDto<List<PopularSearchEntity>> getPopularSearchList() {
        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();

        try {
            popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success", popularSearchList);
    }


    public ResponseDto<List<BoardEntity>> getSearchList(String boardTitle){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList = boardRepository.findByBoardTitleContains(boardTitle);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed("Databaase Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }
}
