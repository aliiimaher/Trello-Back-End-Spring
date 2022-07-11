package project.trello.service;


import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.Archive;
import project.trello.model.Board;
import project.trello.model.Card;
import project.trello.model.Users;
import project.trello.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private final CardRepository cardRepository;
    private final ListRepository listRepository;
    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;

    public ArchiveService(ArchiveRepository archiveRepository, CardRepository cardRepository, ListRepository listRepository, BoardRepository boardRepository, UsersRepository usersRepository) {
        this.archiveRepository = archiveRepository;
        this.cardRepository = cardRepository;
        this.listRepository = listRepository;
        this.boardRepository = boardRepository;
        this.usersRepository = usersRepository;
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
        Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
        Long listId = cardRepository.findById(card_id).get().getList_id();
        project.trello.model.List thisList = listRepository.findById(listId).get();
        String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                " archive card with " + card.getTitle() + " title " +
                "from " + thisList.getTitle() + " list.";
        Long boardId = thisList.getBoard_id();
        boardRepository.findById(boardId).get().getActivityList().add(msg);
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

    public void addListToArchive(Long list_id) {
        if (archiveRepository.findAll().isEmpty()){
            List<Card> card_list = new ArrayList<>();
            List<project.trello.model.List> list_list = new ArrayList<>();
            Archive archive = new Archive(list_list,card_list);
            archiveRepository.save(archive);
        }
        Archive archive1 = archiveRepository.findById(1L).get();
        project.trello.model.List list = listRepository.findById(list_id).get();
        archive1.getLists().add(list);
        archiveRepository.save(archive1);
        List<Board> boards = boardRepository.findAll();
        for(Board board : boards){
            for(project.trello.model.List list1 : board.getLists()){
                if(list1.getId().equals(list_id)){
                    board.getLists().remove(list);
                    boardRepository.save(board);
                    return;
                }
            }
        }
    }

    public List<Archive> getArchives() {
        return archiveRepository.findAll();
    }
}
