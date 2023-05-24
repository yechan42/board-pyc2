package idusw.springboot.controller;


import idusw.springboot.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")

public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    @GetMapping(value = {"/",""})
    public String getBoardList(Model model){
        model.addAttribute("key","value");
        return "/boards?list"; // board/list.html 뷰로 전달
    }

}
