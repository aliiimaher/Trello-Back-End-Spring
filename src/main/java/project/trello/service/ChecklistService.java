package project.trello.service;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.trello.model.Checklist;
import project.trello.repository.ChecklistRepository;

import java.util.List;

@Service
public class ChecklistService {


    private final ChecklistRepository checklistRepository;

    @Autowired
    public ChecklistService(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    public List<Checklist> getChecklists() {
        return checklistRepository.findAll();
    }


    public Checklist createChecklist(Checklist checklist) {
        return checklistRepository.save(checklist);
    }
}
