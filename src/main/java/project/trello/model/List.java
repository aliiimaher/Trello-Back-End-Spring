package project.trello.model;

import lombok.*;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;


}
