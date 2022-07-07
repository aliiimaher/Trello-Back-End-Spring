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

    public void deleteBoard(Long board_id) {
        boolean exists = boardRepository.existsById(board_id);
        if (!exists) {
            throw new IllegalStateException("board with id " +
                    board_id + " does not exist");
        }
        boardRepository.deleteById(board_id);
    }


    public Board editBoard(Long board_id, Board board) {
        Board foundedBoard = boardRepository.findById(board_id).get();
        foundedBoard.setTitle(board.getTitle());
        foundedBoard.setVisibility(board.getVisibility());

        return boardRepository.save(foundedBoard);
    }
}
