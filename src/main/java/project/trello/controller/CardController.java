package project.trello.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Card;
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
}
