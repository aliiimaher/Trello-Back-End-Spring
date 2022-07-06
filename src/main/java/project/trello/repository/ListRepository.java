package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.List;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
}