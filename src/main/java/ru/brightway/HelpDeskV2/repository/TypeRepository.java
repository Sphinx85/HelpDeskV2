package ru.brightway.HelpDeskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.brightway.HelpDeskV2.Entites.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
