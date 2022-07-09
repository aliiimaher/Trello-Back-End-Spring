package project.trello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;
import project.trello.repository.WorkspaceRepository;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, WorkspaceRepository workspaceRepository) {
        this.boardRepository = boardRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    public Workspace createBoard(Long workspace_id,Board board){
        boardRepository.save(board);
        Workspace workspace = workspaceRepository.findById(workspace_id).get();
        Board board1 = boardRepository.findById(board.getId()).get();
        workspace.getBoards().add(board1);
        return workspaceRepository.save(workspace);
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
