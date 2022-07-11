package project.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.trello.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
