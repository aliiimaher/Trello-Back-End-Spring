package project.trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Card;
import project.trello.model.Label;
import project.trello.service.CardService;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("create-card")
    public Card createCard(@RequestBody Card card){
        return cardService.createCard(card);
    }

    // ------------------------- delete card ------ :")
//    @DeleteMapping("delete-card/{card_id}")
//    public void deleteCard(@PathVariable("card_id") Long card_id) {
//        cardService.deleteCard(card_id);
//    }

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

}
