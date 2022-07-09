package project.trello.service;


import org.springframework.stereotype.Service;
import project.trello.repository.ArchiveRepository;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }
}
