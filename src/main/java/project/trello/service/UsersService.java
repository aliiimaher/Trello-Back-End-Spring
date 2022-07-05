package project.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Users;
import project.trello.repository.UsersRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public Users createUser(Users user) {
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
        for(Users user : usersList){
            if(user.getEmail().equals(user1.getEmail()) && user.getPassword().equals(user1.getPassword())){
                return "Login Was Successful !!";
            }
        }
        return "Wrong Password Or Username !";
    }
}
