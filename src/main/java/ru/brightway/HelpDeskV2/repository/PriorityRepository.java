package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}