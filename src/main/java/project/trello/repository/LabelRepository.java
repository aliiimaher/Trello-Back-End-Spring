package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}
