package project.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
    public static Long user_id;
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
