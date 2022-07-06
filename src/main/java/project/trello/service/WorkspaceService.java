package project.trello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;
import project.trello.repository.WorkspaceRepository;

import java.util.List;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository,BoardRepository boardRepository) {
        this.workspaceRepository = workspaceRepository;
        this.boardRepository = boardRepository;
    }


    public List<Workspace> getWorkspaces(){
        return workspaceRepository.findAll();
    }

    public Workspace createWorkspace(Workspace workspace){
        return workspaceRepository.save(workspace);
    }

    public  Workspace assignBoardToWorkspace(Long board_id,Long workspace_id){
        Board board = boardRepository.findById(board_id).get();
        Workspace workspace = workspaceRepository.findById(workspace_id).get();
        workspace.getBoards().add(board);
        return workspaceRepository.save(workspace);
    }



}
