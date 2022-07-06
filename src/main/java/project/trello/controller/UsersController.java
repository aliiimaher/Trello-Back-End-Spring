package project.trello.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.*;
import project.trello.model.Users;
import project.trello.model.Workspace;
import project.trello.service.UsersService;

import java.util.List;

@RestController
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

    @PutMapping("/assignUserToWorkspace/{user_id}/{workspace_id}")
    public Users assignUserToWorkspace(@PathVariable Long user_id, @PathVariable Long workspace_id){
        return usersService.assignUserToWorkspace(user_id,workspace_id);
    }

    @PutMapping("/update-user/{user_id}")
    public Users updateUser(@PathVariable Long user_id, @RequestBody Users users){
    return usersService.updateUser(user_id , users);
    }
}