package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Card;
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

    @PutMapping("/create-checklist/{card_id}")
    public Card createChecklist(@PathVariable Long card_id, @RequestBody Checklist checklist){
        return checklistService.createChecklist(card_id,checklist);
    }

    @PutMapping("/add-item/{checklist_id}")
    public Checklist addItem(@PathVariable Long checklist_id,@RequestBody Checklist checklist){
        return checklistService.addItem(checklist_id,checklist.getItems().get(0));
    }

    @DeleteMapping("/delete-checklist/{checklist_id}")
    public void deleteChecklist(@PathVariable Long checklist_id) {
        checklistService.deleteChecklist(checklist_id);
    }

}
