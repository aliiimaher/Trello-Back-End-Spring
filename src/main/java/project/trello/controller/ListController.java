package project.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.trello.service.ListService;

import java.util.List;

@RestController
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }


    @GetMapping("getlists")
    public List<project.trello.model.List> getLists(){
        return listService.getLists();
    }

    @PostMapping("createlist")
    public project.trello.model.List createBoard(@RequestBody project.trello.model.List list){
        return listService.createList(list);
    }

}