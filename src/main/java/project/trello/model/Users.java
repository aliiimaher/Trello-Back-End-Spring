package project.trello.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;


    public Users(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Users(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}

// this fucking test for git fucking push and motherfuncking committttttttttttttttttttttttt :")