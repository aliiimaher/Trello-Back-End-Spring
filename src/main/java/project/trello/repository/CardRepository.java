package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}


