package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Workspace;
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
    public Workspace createWorkspace(@RequestBody Workspace workspace){
        return workspaceService.createWorkspace(workspace);
    }

    @PutMapping("/assignBoardToWorkspace/{board_id}/{workspace_id}")
    public Workspace assignBoardToWorkspace(@PathVariable Long board_id,@PathVariable Long workspace_id){
        return workspaceService.assignBoardToWorkspace(board_id,workspace_id);
    }


}
