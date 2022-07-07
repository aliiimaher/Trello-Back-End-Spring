package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Card;
import project.trello.model.Label;
import project.trello.repository.CardRepository;
import project.trello.repository.LabelRepository;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public CardService(CardRepository cardRepository, LabelRepository labelRepository) {
        this.cardRepository = cardRepository;
        this.labelRepository = labelRepository;
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

    public Card addLabel(Long card_id,Label label){
        labelRepository.save(label);
        Card card = cardRepository.findById(card_id).get();
        Label label1 = labelRepository.findById(label.getId()).get();
        card.getLabels().add(label1);
        return cardRepository.save(card);
    }

    public void deleteLabel(Long label_id){
        boolean exists = labelRepository.existsById(label_id);
        if (!exists) {
            throw new IllegalStateException("lebel with id " + label_id + " does not exists");
        }
        labelRepository.deleteById(label_id);
    }

    public Label editLabel(Long label_id, Label label) {
        Label label1 = labelRepository.findById(label_id).get();
        label1.setColor(label.getColor());
        label1.setName(label.getName());
        return labelRepository.save(label1);
    }
}
