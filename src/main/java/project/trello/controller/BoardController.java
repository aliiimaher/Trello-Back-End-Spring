package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.service.BoardService;

import java.util.List;

@RestController
public class BoardController {

    private  final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("getboards")
    public List<Board> getBoards(){
        return boardService.getBoards();
    }

    @PutMapping("/createboard/{workspace_id}")
    public Workspace createBoard(@PathVariable Long workspace_id,@RequestBody Board board){
        return boardService.createBoard(workspace_id,board);
    }

    @DeleteMapping("/delete-board/{board_id}")
    public void deleteBoard(@PathVariable("board_id") Long board_id) {
        boardService.deleteBoard(board_id);
    }

    @PutMapping("/edit-board/{board_id}")
    public Board editBoard(@PathVariable Long board_id, @RequestBody Board board){
        return boardService.editBoard(board_id, board);
    }

    @GetMapping("/get-activities/{board_id}")
    public List<String> getActivities(@PathVariable Long board_id) {
        return boardService.getActivities(board_id);
    }
}
