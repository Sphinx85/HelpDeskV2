package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.KeyWords;

public interface KeyWordsRepository extends JpaRepository<KeyWords, Integer> {
}
