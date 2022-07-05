package project.trello.model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Checklist {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String title;

    @ElementCollection
    private List<String> items;



}
