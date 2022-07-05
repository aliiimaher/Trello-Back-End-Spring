package project.trello.model;

import lombok.*;

import javax.persistence.*;


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



    public Board(String title, String visibility) {
        this.title = title;
        this.visibility = visibility;
    }


}
