package ru.brightway.HelpDeskV2.services.interfaces;

import ru.brightway.HelpDeskV2.Entites.Priority;

import java.util.List;

public interface PriorityService {
    Priority saveUser (Priority priority);
    void deletePriority(Integer priorityId);
    List<Priority> findAll();
}
