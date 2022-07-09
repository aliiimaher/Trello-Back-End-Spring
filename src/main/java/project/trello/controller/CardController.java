package project.trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.*;
import project.trello.service.ArchiveService;
import project.trello.service.CardService;

import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;
    private final ArchiveService archiveService;

    @Autowired
    public CardController(CardService cardService, ArchiveService archiveService) {
        this.cardService = cardService;
        this.archiveService = archiveService;
    }

    @PutMapping("/create-card/{list_id}")
    public project.trello.model.List createCard(@PathVariable Long list_id,@RequestBody Card card){
        return cardService.createCard(list_id,card);
    }
    @GetMapping("getcards")
    public List<Card> getCards(){
        return cardService.getCards();
    }

    // ------------------------- delete card ------ :")
    @DeleteMapping("delete-card/{card_id}")
    public void deleteCard(@PathVariable("card_id") Long card_id) {
        cardService.deleteCard(card_id);
    }

    @PutMapping("/edit-card/{card_id}")
    public Card editCard(@PathVariable Long card_id,@RequestBody Card card){
       return cardService.editCard(card_id,card);
    }

    @PutMapping("/add-label/{card_id}")
    public Card addLabel(@PathVariable Long card_id, @RequestBody Label label){
        return cardService.addLabel(card_id,label);
    }

    @DeleteMapping("delete-label/{label_id}")
    public void deleteLabel(@PathVariable("label_id") Long label_id) {
        cardService.deleteLabel(label_id);
    }

    @PutMapping("/edit-label/{label_id}")
    public Label editLabel(@PathVariable Long label_id,@RequestBody Label label){
        return cardService.editLabel(label_id,label);
    }

    @PutMapping("/add-comment/{card_id}")
    public Card addComment(@PathVariable Long card_id, @RequestBody Comment comment){
        return cardService.addComment(card_id, comment);
    }

    @PutMapping("/addCardToArchive/{card_id}")
    public void addCardToArchive(@PathVariable Long card_id){
        archiveService.addCardToArchive(card_id);
    }

    @GetMapping("get-cardarchive")
    public List<Archive> getArchive(){
        return archiveService.getCardArchives();
    }

}
