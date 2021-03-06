package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.ProjectApplication;
import project.trello.model.Users;
import project.trello.model.Workspace;
import project.trello.service.UsersService;
import project.trello.service.WorkspaceService;

import java.util.List;

@RestController
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }


    @GetMapping("getworkspaces")
    public List<Workspace> getWorkspaces(){
        return workspaceService.getWorkspaces();
    }

    @PostMapping("createworkspace")
    public Users createWorkspace(@RequestBody Workspace workspace){
        return workspaceService.createWorkspace(ProjectApplication.user_id,workspace);
    }

    @PutMapping("/assignBoardToWorkspace/{board_id}/{workspace_id}")
    public Workspace assignBoardToWorkspace(@PathVariable Long board_id,@PathVariable Long workspace_id){
        return workspaceService.assignBoardToWorkspace(board_id,workspace_id);
    }

    @DeleteMapping("/delete-workspace/{workspace_id}")
    public void deleteWorkspace(@PathVariable("workspace_id") Long workspace_id) {
        workspaceService.deleteWorkspace(workspace_id);
    }

    @PutMapping("/edit-workspace/{workspace_id}")
    public Workspace editWorkspace(@PathVariable Long workspace_id, @RequestBody Workspace workspace){
        return workspaceService.editWorkspace(workspace_id, workspace);
    }

    @GetMapping("get-userworkspaces")
    public List<Workspace> userWorkspaces(){
        return workspaceService.userWorkspaces(ProjectApplication.user_id);
    }

}
