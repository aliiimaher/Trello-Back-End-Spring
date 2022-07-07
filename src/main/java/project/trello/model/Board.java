package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "activity_id")
    private List<Activity> activities;

    public Board(String title, String visibility, List<project.trello.model.List> lists, List<Activity> activities) {
        this.title = title;
        this.visibility = visibility;
        this.lists = lists;
        this.activities = activities;
    }
}
