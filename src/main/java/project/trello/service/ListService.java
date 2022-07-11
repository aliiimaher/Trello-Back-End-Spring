package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.ProjectApplication;
import project.trello.model.Archive;
import project.trello.model.Board;
import project.trello.model.Users;
import project.trello.repository.ArchiveRepository;
import project.trello.repository.BoardRepository;
import project.trello.repository.ListRepository;
import project.trello.repository.UsersRepository;

import java.util.List;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final BoardRepository boardRepository;
    private final ArchiveRepository archiveRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    @Autowired
    public ListService(ListRepository listRepository, BoardRepository boardRepository, ArchiveRepository archiveRepository, UsersRepository usersRepository, UsersService usersService) {
        this.listRepository = listRepository;
        this.boardRepository = boardRepository;
        this.archiveRepository = archiveRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    public List<project.trello.model.List> getLists() {
        return listRepository.findAll();
    }

    public Board createList(Long board_id,project.trello.model.List list) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Board board = boardRepository.findById(board_id).get();
        Long workspace_id = board.getWorkspace_id();
        if (usersService.isAdmin(maybeAdmin_id, workspace_id)) {
            list.setBoard_id(board_id);
            Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
            String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                    " create list with " + list.getTitle() + " title.";
            boardRepository.findById(board_id).get().getActivityList().add(msg);
            listRepository.save(list);
            project.trello.model.List list1 = listRepository.findById(list.getId()).get();
            board.getLists().add(list1);
            return boardRepository.save(board);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public project.trello.model.List editList(Long list_id, project.trello.model.List list) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Long board_id = listRepository.findById(list_id).get().getBoard_id();
        Board board = boardRepository.findById(board_id).get();
        if (usersService.isAdmin(maybeAdmin_id, board.getWorkspace_id())) {
            project.trello.model.List foundedList = listRepository.findById(list_id).get();
            Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
            String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                    " edit a list.";
            boardRepository.findById(board_id).get().getActivityList().add(msg);
            foundedList.setTitle(list.getTitle());
            return listRepository.save(foundedList);
        }
        throw new IllegalStateException("You are not an admin :/");
    }

    public void deleteList(Long list_id) {
        Long maybeAdmin_id = ProjectApplication.user_id;
        Long board_id = listRepository.findById(list_id).get().getBoard_id();
        Board board = boardRepository.findById(board_id).get();
        if (usersService.isAdmin(maybeAdmin_id, board.getWorkspace_id())) {
            boolean exists = listRepository.existsById(list_id);
            if (!exists) {
                throw new IllegalStateException("list with id " + list_id +
                        " does not exist!");
            }
            if (archiveRepository.findAll().isEmpty()) {
                throw new IllegalStateException("archive is empty !");
            }
            Archive archive = archiveRepository.findById(1L).get();
            List<project.trello.model.List> archiveLists = archive.getLists();
            for (project.trello.model.List list : archiveLists) {
                if (list.getId().equals(list_id)) {
                    Users thisUser = usersRepository.findById(ProjectApplication.user_id).get();
                    String msg = thisUser.getFirstName() + " " + thisUser.getLastName() +
                            " delete list with " + list.getTitle() + " title.";
                    boardRepository.findById(board_id).get().getActivityList().add(msg);
                    listRepository.deleteById(list_id);
                    return;
                }
            }
            throw new IllegalStateException("you should archive this list first !");
        }
        throw new IllegalStateException("You are not an admin :/");
    }

}