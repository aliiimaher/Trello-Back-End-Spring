package project.trello.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String title;
    private String visibility;      // it can be "private" -- "workspace" -- "public"    .

    @OneToMany
    @JoinColumn(name = "list_id")
    private List<project.trello.model.List> lists;

    @OneToMany
    @JoinColumn(name = "activity_id")
    private List<Activity> activities;

    public Board(String title, String visibility) {
        this.title = title;
        this.visibility = visibility;
    }


}
