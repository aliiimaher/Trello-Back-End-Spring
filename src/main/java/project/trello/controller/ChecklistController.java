package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.trello.model.Checklist;
import project.trello.service.ChecklistService;

import java.util.List;

@RestController
public class ChecklistController {


    private final ChecklistService checklistService;

    @Autowired
    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping("get-checklists")
    public List<Checklist> getChecklists(){
        return checklistService.getChecklists();
    }

}
