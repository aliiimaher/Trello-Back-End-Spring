package project.trello.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany
    @JoinColumn(name = "lists_id")
    private List<project.trello.model.List> lists;

    @OneToMany
    @JoinColumn(name = "cards_id")
    private List<Card> cards;

    public Archive(List<project.trello.model.List> lists, List<Card> cards) {
        this.lists = lists;
        this.cards = cards;
    }
}
