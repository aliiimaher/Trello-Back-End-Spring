package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import project.trello.ProjectApplication;
import project.trello.model.Users;
import project.trello.model.Workspace;
import project.trello.repository.UsersRepository;
import project.trello.repository.WorkspaceRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsersService {


    private final UsersRepository usersRepository;
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, WorkspaceRepository workspaceRepository) {
        this.usersRepository = usersRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users signup(Users user) {
        Optional<Users> usersByEmail = usersRepository.findUsersByEmail(user.getEmail());
        if(usersByEmail.isPresent()){
            throw new IllegalStateException("Email is already taken by another user !");
        }
        return usersRepository.save(user);
    }

    public String login(String email, String password) {
        List<Users> usersList;
        usersList = getUsers();
        Users user1 = new Users(email,password);
        for(Users users : usersList){
            if(users.getPassword().equals(user1.getPassword()) && users.getEmail().equals(user1.getEmail())){
                ProjectApplication.user_id = users.getId();
                return "Login Was Successful !!";
            }
        }
        return "Wrong Password Or Username !";
    }

    public Users assignUserToWorkspace(Long user_id,Long workspace_id){
        Users user = usersRepository.findById(user_id).get();
        Workspace workspace = workspaceRepository.findById(workspace_id).get();
        user.getWorkspaces().add(workspace);
        return usersRepository.save(user);
    }

    public Users updateUser(Long user_id , Users user){
        Users users = usersRepository.findById(user_id).get();
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());

        return usersRepository.save(users);

    }

    public void deleteUser(Long user_id) {
        boolean exists = usersRepository.existsById(user_id);
        if (!exists) {
            throw new IllegalStateException("user with id " + user_id + " does not exists");
        }
        usersRepository.deleteById(user_id);
    }

    public void promoteMember(Long user_id,Long workspace_id) {
        if(!usersRepository.findById(user_id).isPresent()){
            throw new IllegalStateException("user with id "+user_id+" does not exist");
        }
        List<Workspace> workspaces = workspaceRepository.findAll();
        for(Workspace workspace : workspaces){
            if(workspace.getId().equals(workspace_id)){
                if(workspace.getIdOfAdmins().contains(user_id)){
                    throw new IllegalStateException("user with id "+user_id+" is already admin");
                }
                workspace.getIdOfAdmins().add(user_id);
                workspaceRepository.save(workspace);
                return;
            }
        }
        throw new IllegalStateException("workspace with id "+workspace_id+" does not exist");
    }

}
