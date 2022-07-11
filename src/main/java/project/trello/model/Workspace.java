package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workspace {

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;         // workspace name
    private String type;         // it can be education,marketing,small business, ...
    private String description;
    private String visibility;   // it can be private and public .

    @OneToMany
    @JoinColumn(name = "workspace_id")
    private List<Board> boards;

    @ElementCollection
    private List<Long> idOfAdmins = new ArrayList<>();



    public Workspace(String name, String type, String description,
                     String visibility, List<Board> boards,
                     List<Long> idOfAdmins) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.visibility = visibility;
        this.boards = boards;
        this.idOfAdmins = idOfAdmins;
    }


}
