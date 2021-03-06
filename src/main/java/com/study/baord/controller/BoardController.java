package com.study.baord.controller;

import com.study.baord.entity.Board;
import com.study.baord.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWrtiteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {

        boardService.boardWrite(board, file);

        model.addAttribute("message","글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList( Model model ) {

        model.addAttribute("list", boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view")  // localhost:8080/board/view?id=1 -> get 방식의 parameter
    public String boardView( Model model, Integer id ) {

        model.addAttribute("board", boardService.boardView(id) );
        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete( Integer id ) {
        System.out.println("######" + id);
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify( @PathVariable("id") Integer id, Model model ) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate( @PathVariable("id") Integer id, Board board, MultipartFile file ) throws Exception {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        System.out.println("#########################################");
        System.out.println( boardTemp.getTitle() );
        System.out.println( boardTemp.getContent() );

        boardService.boardWrite(boardTemp, file);

        return "redirect:/board/list";
    }



}
