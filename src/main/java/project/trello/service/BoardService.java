package project.trello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

}
