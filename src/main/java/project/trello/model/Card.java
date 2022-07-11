package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;


    @OneToMany
    @JoinColumn(name = "checklist_id" )
    private List<Checklist> checklists;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Users> users;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "lebel_id")
    private List<Label> labels;

    @OneToMany
    @JoinColumn(name = "activity_id")
    private List<Activity> activities;

    @JsonIgnore
    private Long list_id;
}
