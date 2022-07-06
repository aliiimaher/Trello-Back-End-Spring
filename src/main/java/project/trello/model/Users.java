package project.trello.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;


    @ManyToMany
    @JoinTable(
            name = "workspace_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workspace_id")
    )
    private List<Workspace> workspaces;


    public Users(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Users(String firstName, String lastName, String password, String email, List<Workspace> workspaces) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.workspaces = workspaces;
    }

    public Users(String firstName , String lastName, String kir){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
