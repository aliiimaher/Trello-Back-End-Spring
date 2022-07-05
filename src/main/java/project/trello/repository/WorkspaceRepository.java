package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
