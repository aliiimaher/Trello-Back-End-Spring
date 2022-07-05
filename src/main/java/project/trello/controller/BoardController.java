package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("board")
public class BoardController {

    private  final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("get")
    public List<Board> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("create")
    public Board createBoard(@RequestBody Board board){
        return boardService.createBoard(board);
    }
}
