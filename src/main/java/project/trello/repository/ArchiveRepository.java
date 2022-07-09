package project.trello.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Archive;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive,Long> {
}
