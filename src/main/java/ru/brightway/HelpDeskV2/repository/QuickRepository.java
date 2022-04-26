package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.QuickMessages;

public interface QuickRepository extends JpaRepository<QuickMessages, Integer> {
}
