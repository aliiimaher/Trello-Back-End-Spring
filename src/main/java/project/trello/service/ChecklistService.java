package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.Board;
import project.trello.model.Card;
import project.trello.model.Checklist;
import project.trello.repository.BoardRepository;
import project.trello.repository.CardRepository;
import project.trello.repository.ChecklistRepository;
import project.trello.repository.ListRepository;

import java.util.List;

@Service
public class ChecklistService {


    private final ChecklistRepository checklistRepository;
    private final CardRepository cardRepository;
    private final ListRepository listRepository;
    private final BoardRepository boardRepository;
    private final UsersService usersService;


    @Autowired
    public ChecklistService(ChecklistRepository checklistRepository, CardRepository cardRepository, ListRepository listRepository, BoardRepository boardRepository, UsersService usersService) {
        this.checklistRepository = checklistRepository;
        this.cardRepository = cardRepository;
        this.listRepository = listRepository;
        this.boardRepository = boardRepository;
        this.usersService = usersService;
    }


    public List<Checklist> getChecklists() {
        return checklistRepository.findAll();
    }

    public Card createChecklist(Long card_id,Checklist checklist) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Card thisCard = cardRepository.findById(card_id).get();
        Long list_id = thisCard.getList_id();
        project.trello.model.List thisList = listRepository.findById(list_id).get();
        Long board_id = thisList.getBoard_id();
        Board thisBoard = boardRepository.findById(board_id).get();
        Long workspace_id = thisBoard.getWorkspace_id();
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            checklistRepository.save(checklist);
            Card card = cardRepository.findById(card_id).get();
            Checklist checklist1 = checklistRepository.findById(checklist.getId()).get();
            card.getChecklists().add(checklist1);
            return cardRepository.save(card);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public Checklist addItem(Long checklist_id,String item){
        Checklist checklist = checklistRepository.findById(checklist_id).get();
        checklist.getItems().add(item);
        return checklistRepository.save(checklist);
    }
}
