package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String label;


    public Card(Long id, String title, String description, String label,
                List<Checklist> checklists, List<Users> users,
                List<Comment> comments, List<Activity> activities) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.label = label;
        this.checklists = checklists;
        this.users = users;
        this.comments = comments;
        this.activities = activities;
    }

    public Card(String title, String description, String label,
                List<Checklist> checklists, List<Users> users,
                List<Comment> comments, List<Activity> activities) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.checklists = checklists;
        this.users = users;
        this.comments = comments;
        this.activities = activities;
    }

    @OneToMany
    @JoinColumn(name = "checklist_id" )
    private List<Checklist> checklists;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Users> users;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "activity_id")
    private List<Activity> activities;

}
