package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Card;
import project.trello.model.Checklist;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
