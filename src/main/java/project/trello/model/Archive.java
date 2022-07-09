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
    @JoinColumn(name = "list_id")
    private List<project.trello.model.List> lists;

    @OneToMany
    @JoinColumn(name = "card_id")
    private List<Card> cards;

}
