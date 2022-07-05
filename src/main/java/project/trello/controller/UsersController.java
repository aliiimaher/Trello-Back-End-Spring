package project.trello.controller;


import org.springframework.web.bind.annotation.*;
import project.trello.model.Users;
import project.trello.model.Workspace;
import project.trello.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("getusers")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping("signup")
    public Users signup(@RequestBody Users user){
        return usersService.createUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user){
        return usersService.login(user.getEmail(),user.getPassword());
    }

    @PutMapping("/{user_id}/asignUserToWorkspace/{workspace_id}")
    public Users asignUserToWorkspace(@PathVariable Long user_id, @PathVariable Long workspace_id){
        return usersService.asignUserToWorkspace(user_id,workspace_id);
    }
}
