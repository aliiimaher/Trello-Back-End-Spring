package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Board;
import project.trello.model.Workspace;
import project.trello.repository.BoardRepository;
import project.trello.repository.ListRepository;

import java.util.List;

@Service
public class ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<project.trello.model.List> getLists() {
        return listRepository.findAll();
    }

    public project.trello.model.List createList(project.trello.model.List list) {
        return listRepository.save(list);
    }

    public project.trello.model.List editList(Long list_id, project.trello.model.List list) {
        project.trello.model.List foundedList = listRepository.findById(list_id).get();
        foundedList.setTitle(list.getTitle());

        return listRepository.save(foundedList);
    }
}