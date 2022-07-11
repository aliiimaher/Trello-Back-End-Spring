package project.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String commenterName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
