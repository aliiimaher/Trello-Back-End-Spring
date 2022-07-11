package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany
    @JoinColumn(name = "card_id")
    private java.util.List<Card> cards;

    @OneToMany
    @JoinColumn(name = "activity_id")
    private java.util.List<Activity> activities;

    @JsonIgnore
    private Long board_id;
}
