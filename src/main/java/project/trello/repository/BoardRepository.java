package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import project.trello.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
