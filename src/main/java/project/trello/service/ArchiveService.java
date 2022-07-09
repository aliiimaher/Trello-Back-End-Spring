package project.trello.service;


import org.springframework.stereotype.Service;
import project.trello.model.Archive;
import project.trello.model.Board;
import project.trello.model.Card;
import project.trello.repository.ArchiveRepository;
import project.trello.repository.CardRepository;
import project.trello.repository.ListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private final CardRepository cardRepository;
    private final ListRepository listRepository;

    public ArchiveService(ArchiveRepository archiveRepository, CardRepository cardRepository, ListRepository listRepository) {
        this.archiveRepository = archiveRepository;
        this.cardRepository = cardRepository;
        this.listRepository = listRepository;
    }


    public void addCardToArchive(Long card_id) {
        if (archiveRepository.findAll().isEmpty()){
            List<Card> card_list = new ArrayList<>();
            List<project.trello.model.List> list_list = new ArrayList<>();
            Archive archive = new Archive(list_list,card_list);
            archiveRepository.save(archive);
        }
        Archive archive1 = archiveRepository.findById(1L).get();
        Card card = cardRepository.findById(card_id).get();
        archive1.getCards().add(card);
        archiveRepository.save(archive1);
        List<project.trello.model.List> lists = listRepository.findAll();
        for(project.trello.model.List list : lists){
            for(Card card1 : list.getCards()){
                if(card1.getId().equals(card_id)){
                    list.getCards().remove(card1);
                    listRepository.save(list);
                    return;
                }
            }
        }
    }

    public List<Archive> getCardArchives() {
        return archiveRepository.findAll();
    }
}
