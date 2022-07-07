package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
