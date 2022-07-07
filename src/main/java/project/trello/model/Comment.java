package project.trello.model;

import lombok.*;
import javax.persistence.*;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
