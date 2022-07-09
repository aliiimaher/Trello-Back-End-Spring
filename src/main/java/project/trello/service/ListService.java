package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Board;
import project.trello.repository.BoardRepository;
import project.trello.repository.ListRepository;

import java.util.List;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public ListService(ListRepository listRepository, BoardRepository boardRepository) {
        this.listRepository = listRepository;
        this.boardRepository = boardRepository;
    }

    public List<project.trello.model.List> getLists() {
        return listRepository.findAll();
    }

    public Board createList(Long board_id,project.trello.model.List list) {
        listRepository.save(list);
        Board board = boardRepository.findById(board_id).get();
        project.trello.model.List list1 = listRepository.findById(list.getId()).get();
        board.getLists().add(list1);
        return boardRepository.save(board);
    }

    public project.trello.model.List editList(Long list_id, project.trello.model.List list) {
        project.trello.model.List foundedList = listRepository.findById(list_id).get();
        foundedList.setTitle(list.getTitle());

        return listRepository.save(foundedList);
    }
}