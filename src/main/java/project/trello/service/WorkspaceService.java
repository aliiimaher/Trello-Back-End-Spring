package project.trello.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.Board;
import project.trello.model.Users;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;
import project.trello.repository.UsersRepository;
import project.trello.repository.WorkspaceRepository;

import java.util.List;

@Service
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UsersRepository usersRepository;
    private final BoardRepository boardRepository;
    private final UsersService usersService;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository, UsersRepository usersRepository, BoardRepository boardRepository, UsersService usersService) {
        this.workspaceRepository = workspaceRepository;
        this.usersRepository = usersRepository;
        this.boardRepository = boardRepository;
        this.usersService = usersService;
    }

    public List<Workspace> getWorkspaces(){
        return workspaceRepository.findAll();
    }

    public Users createWorkspace(Long user_id,Workspace workspace){
        workspaceRepository.save(workspace);
        Users user = usersRepository.findById(user_id).get();
        user.getWorkspaces().add(workspace);

        Workspace workspace1 = workspaceRepository.findById(workspace.getId()).get();
        workspace1.getIdOfAdmins().add(user_id);
        workspaceRepository.save(workspace1);
        return usersRepository.save(user);
    }

    // Good Feeling ^~^
    public  Workspace assignBoardToWorkspace(Long board_id,Long workspace_id){
        Board board = boardRepository.findById(board_id).get();
        Workspace workspace = workspaceRepository.findById(workspace_id).get();
        workspace.getBoards().add(board);
        return workspaceRepository.save(workspace);
    }

    public void deleteWorkspace(Long workspace_id) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            boolean exists = workspaceRepository.existsById(workspace_id);
            if (!exists) {
                throw new IllegalStateException("workspace with id " +
                        workspace_id + " does not exist");
            }
            workspaceRepository.deleteById(workspace_id);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public Workspace editWorkspace(Long workspace_id, Workspace workspace) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            Workspace foundedWorkspace = workspaceRepository.findById(workspace_id).get();
            foundedWorkspace.setName(workspace.getName());
            foundedWorkspace.setVisibility(workspace.getVisibility());
            return workspaceRepository.save(foundedWorkspace);
        }
        throw new IllegalStateException("You are not an admin :/");
    }


    public List<Workspace> userWorkspaces(Long user_id) {
        Users user = usersRepository.findById(user_id).get();
        List<Workspace> userWorkspaces = user.getWorkspaces();
        return userWorkspaces;
    }
}
