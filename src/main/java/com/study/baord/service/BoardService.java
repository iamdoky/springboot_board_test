package com.study.baord.service;

import com.study.baord.entity.Board;
import com.study.baord.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 등록
    public void boardWrite(Board board) {
        boardRepository.save(board);
    }

    // 게시글 목록
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    // 게시글 상세
    public Board boardView (Integer id) {

        return boardRepository.findById(id).get();
    }

    // 게시글 삭제
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }


}
