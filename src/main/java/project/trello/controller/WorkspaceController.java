package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Workspace;
import project.trello.service.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }


    @GetMapping("get")
    public List<Workspace> getWorkspaces(){
        return workspaceService.getWorkspaces();
    }

    @PostMapping("create")
    public Workspace createWorkspace(@RequestBody Workspace workspace){
        return workspaceService.createWorkspace(workspace);
    }

    @PutMapping("/{board_id}/asignBoardToWorkspace/{workspace_id}")
    public Workspace asignBoardToWorkspace(@PathVariable Long board_id,@PathVariable Long workspace_id){
        return workspaceService.asignBoardToWorkspace(board_id,workspace_id);
    }


}
