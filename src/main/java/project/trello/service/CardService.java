package project.trello.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.*;
import project.trello.repository.*;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final LabelRepository labelRepository;
    private final CommentRepository commentRepository;
    private final ListRepository listRepository;
    private final ArchiveRepository archiveRepository;
    private final UsersRepository usersRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public CardService(CardRepository cardRepository,
                       LabelRepository labelRepository,
                       CommentRepository commentRepository,
                       ListRepository listRepository,
                       ArchiveRepository archiveRepository,
                       UsersRepository usersRepository,
                       BoardRepository boardRepository) {
        this.cardRepository = cardRepository;
        this.labelRepository = labelRepository;
        this.commentRepository = commentRepository;
        this.listRepository = listRepository;
        this.archiveRepository = archiveRepository;
        this.usersRepository = usersRepository;
        this.boardRepository = boardRepository;
    }

    public project.trello.model.List createCard(Long list_id, Card card) {
        card.setList_id(list_id);
        cardRepository.save(card);
        project.trello.model.List list = listRepository.findById(list_id).get();
        Card card1 = cardRepository.findById(card.getId()).get();
        list.getCards().add(card1);
        Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
        String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                " Create card by " + card.getTitle() + " title in " +
                listRepository.findById(list_id).get().getTitle() + " list.";
        project.trello.model.List thisList = listRepository.findById(list_id).get();
        Long boardId = thisList.getBoard_id();
        boardRepository.findById(boardId).get().getActivityList().add(msg);
        return listRepository.save(list);
    }

    public List<Card> getCards(){
        return cardRepository.findAll();
    }

    public void deleteCard(Long card_id) {
        boolean exists = cardRepository.existsById(card_id);
        if (!exists) {
            throw new IllegalStateException("card with id " + card_id +
                    " does not exist!");
        }
        if(archiveRepository.findAll().isEmpty()){
            throw new IllegalStateException("archive is empty !");
        }
        Archive archive = archiveRepository.findById(1L).get();
        List<Card> archiveCards = archive.getCards();
        for(Card card : archiveCards){
            if(card.getId().equals(card_id)){
                Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
                Long listId = cardRepository.findById(card_id).get().getList_id();
                project.trello.model.List thisList = listRepository.findById(listId).get();
                String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                        " delete card by " + card.getTitle() + " title " +
                        "from " + thisList.getTitle() + " list.";
                Long boardId = thisList.getBoard_id();
                boardRepository.findById(boardId).get().getActivityList().add(msg);
                cardRepository.deleteById(card_id);
                return;
            }
        }
        throw new IllegalStateException("you should archive this card first !");
    }

    public Card editCard(Long card_id,Card card){
        Card card1 = cardRepository.findById(card_id).get();
        card1.setTitle(card.getTitle());
        card1.setDescription(card.getDescription());
        Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
        Long listId = cardRepository.findById(card_id).get().getList_id();
        project.trello.model.List thisList = listRepository.findById(listId).get();
        String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                " edit a card" + "in " + thisList.getTitle() + " list.";
        Long boardId = thisList.getBoard_id();
        boardRepository.findById(boardId).get().getActivityList().add(msg);
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

    public Card addComment(Long card_id, Comment comment){
        Long user_id = ProjectApplication.user_id;
        Users thisUser = usersRepository.findById(user_id).get();
        comment.setCommenterName(thisUser.getFirstName().concat(thisUser.getLastName()));
        comment.setUser(thisUser);
        commentRepository.save(comment);
        Card card = cardRepository.findById(card_id).get();
        Comment foundedComment = commentRepository.findById(comment.getId()).get();
        Long listId = cardRepository.findById(card_id).get().getList_id();
        project.trello.model.List thisList = listRepository.findById(listId).get();
        String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                " comment on card by " + card.getTitle() + " title " +
                "from " + thisList.getTitle() + " list.";
        Long boardId = thisList.getBoard_id();
        boardRepository.findById(boardId).get().getActivityList().add(msg);
        card.getComments().add(foundedComment);
        return cardRepository.save(card);
    }

    public void assignUserToCard(Long card_id, Long user_id){
        Card thisCard = cardRepository.findById(card_id).get();
        Users thisUser = usersRepository.findById(user_id).get();
        String fullName = thisUser.getFirstName() + " " + thisUser.getLastName();
        thisCard.getCardKeepers().add(fullName);
        cardRepository.save(thisCard);
    }
}
