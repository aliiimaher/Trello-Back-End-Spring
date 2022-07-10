package project.trello.controller;


import org.springframework.web.bind.annotation.*;
import project.trello.model.Users;
import project.trello.service.UsersService;
import project.trello.service.WorkspaceService;

import java.util.List;

@RestController
public class UsersController {

    private final UsersService usersService;
    private final WorkspaceService workspaceService;


    public UsersController(UsersService usersService, WorkspaceService workspaceService) {
        this.usersService = usersService;
        this.workspaceService = workspaceService;
    }

    @GetMapping("getusers")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping("signup")
    public Users signup(@RequestBody Users user){
        return usersService.signup(user);
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

    @DeleteMapping("/delete-user/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long user_id) {
        usersService.deleteUser(user_id);
    }

    @PutMapping("/promote-member/{user_id}/{workspace_id}")
    public void promoteMemberToAdmin(@PathVariable Long user_id,@PathVariable Long workspace_id){
        usersService.promoteMember(user_id,workspace_id);
    }

    @PutMapping("/demote-admin/{user_id}/{workspace_id}")
    public void demoteAdminToMember(@PathVariable Long user_id,@PathVariable Long workspace_id){
        usersService.demoteAdminToMember(user_id,workspace_id);
    }

}