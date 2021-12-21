package com.study.baord.service;

import com.study.baord.entity.Board;
import com.study.baord.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void boardWrite(Board board) {
        boardRepository.save(board);
    }
}
