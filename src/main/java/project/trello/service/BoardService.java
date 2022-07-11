package project.trello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;
import project.trello.repository.WorkspaceRepository;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UsersService usersService;

    @Autowired
    public BoardService(BoardRepository boardRepository, WorkspaceRepository workspaceRepository, UsersService usersService) {
        this.boardRepository = boardRepository;
        this.workspaceRepository = workspaceRepository;
        this.usersService = usersService;
    }

    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    public Workspace createBoard(Long workspace_id,Board board){
        Long maybeAdmin_id = ProjectApplication.user_id;
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            board.setWorkspace_id(workspace_id);
            boardRepository.save(board);
            Workspace workspace = workspaceRepository.findById(workspace_id).get();
            Board board1 = boardRepository.findById(board.getId()).get();
            workspace.getBoards().add(board1);
            return workspaceRepository.save(workspace);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public void deleteBoard(Long board_id) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Long workspace_id = boardRepository.findById(board_id).get().getWorkspace_id();
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            boolean exists = boardRepository.existsById(board_id);
            if (!exists) {
                throw new IllegalStateException("board with id " +
                        board_id + " does not exist");
            }
            boardRepository.deleteById(board_id);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public Board editBoard(Long board_id, Board board) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Long workspace_id = boardRepository.findById(board_id).get().getWorkspace_id();
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            Board foundedBoard = boardRepository.findById(board_id).get();
            foundedBoard.setTitle(board.getTitle());
            //foundedBoard.setVisibility(board.getVisibility());

            return boardRepository.save(foundedBoard);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public List<String> getActivities(Long board_id) {
        return boardRepository.findById(board_id).get().getActivityList();
    }
}
