package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityService {
    void savePriority(Priority priority);
    void deletePriority(Integer priorityId);
    List<Priority> findAll();
    Optional<Priority> findById(int id);
}