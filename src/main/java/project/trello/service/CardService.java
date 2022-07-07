package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Card;
import project.trello.repository.CardRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

// ---------------------------------- delete card ----- :")
//    public void deleteCard(Long card_id) {
//        boolean exists = cardRepository.existsById(card_id);
//        if (!exists) {
//            throw new IllegalStateException("card with id " + card_id +
//                    " does not exist!");
//        }
//        cardRepository.deleteById(card_id);
//    }

    public Card editCard(Long card_id,Card card){
        Card card1 = cardRepository.findById(card_id).get();
        card1.setTitle(card.getTitle());
        card1.setDescription(card.getDescription());

        return cardRepository.save(card1);
    }

}
