package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Card;
import project.trello.model.Checklist;
import project.trello.repository.CardRepository;
import project.trello.repository.ChecklistRepository;

import java.util.List;

@Service
public class ChecklistService {


    private final ChecklistRepository checklistRepository;
    private final CardRepository cardRepository;

    @Autowired
    public ChecklistService(ChecklistRepository checklistRepository, CardRepository cardRepository) {
        this.checklistRepository = checklistRepository;
        this.cardRepository = cardRepository;
    }

    public List<Checklist> getChecklists() {
        return checklistRepository.findAll();
    }


    public Card createChecklist(Long card_id,Checklist checklist) {
        checklistRepository.save(checklist);
        Card card = cardRepository.findById(card_id).get();
        Checklist checklist1 = checklistRepository.findById(checklist.getId()).get();
        card.getChecklists().add(checklist1);
        return cardRepository.save(card);
    }
}
