package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Priority;
import ru.brightway.HelpDeskV2.repository.PriorityRepository;
import ru.brightway.HelpDeskV2.services.interfaces.PriorityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultPriorityService implements PriorityService {
    private final PriorityRepository priorityRepository;
    @Override
    public Priority savePriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public void deletePriority(Integer priorityId) {
        priorityRepository.deleteById(priorityId);
    }

    @Override
    public List<Priority> findAll() {
        return new ArrayList<>(priorityRepository.findAll());
    }

    @Override
    public Optional<Priority> findById(int id) {
        return priorityRepository.findById(id);
    }
}
