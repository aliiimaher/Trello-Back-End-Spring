package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Board;
import project.trello.service.ArchiveService;
import project.trello.service.ListService;

import java.util.List;

@RestController
public class ListController {

    private final ListService listService;
    private final ArchiveService archiveService;

    @Autowired
    public ListController(ListService listService, ArchiveService archiveService) {
        this.listService = listService;
        this.archiveService = archiveService;
    }


    @GetMapping("getlists")
    public List<project.trello.model.List> getLists(){
        return listService.getLists();
    }

    @PutMapping("/createlist/{board_id}")
    public Board createBoard(@PathVariable Long board_id, @RequestBody project.trello.model.List list){
        return listService.createList(board_id,list);
    }

    @PutMapping("/edit-list/{list_id}")
    public project.trello.model.List editList(@PathVariable Long list_id,
                                              @RequestBody project.trello.model.List list){
        return listService.editList(list_id, list);
    }

    @PutMapping("/archive-list/{list_id}")
    public void addListToArchive(@PathVariable Long list_id){
        archiveService.addListToArchive(list_id);
    }
}