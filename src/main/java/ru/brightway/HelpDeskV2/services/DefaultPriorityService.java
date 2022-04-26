package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Priority;
import ru.brightway.HelpDeskV2.repository.PriorityRepository;
import ru.brightway.HelpDeskV2.services.interfaces.PriorityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с приоритетами заявок
 */
@Service
@AllArgsConstructor
public class DefaultPriorityService implements PriorityService {
    @Autowired
    private final PriorityRepository priorityRepository;
    @Getter
    public final int DEFAULT_ID = 1;

    /**
     * Метод сохранения группы приоритетов
     * @param priority Принимает приоритет
     */
    @Override
    public void savePriority(Priority priority) {
        priorityRepository.save(priority);
    }

    /**
     * Метод удаления приоритета по ID
     * @param priorityId Принимает ID приоритета
     */
    @Override
    public void deletePriority(Integer priorityId) {
        priorityRepository.deleteById(priorityId);
    }

    /**
     * Метод поиска всех приоритетов
     * @return Возвращает коллекцию приоритетов
     */
    @Override
    public List<Priority> findAll() {
        return new ArrayList<>(priorityRepository.findAll());
    }

    /**
     * Метод поиска приоритета по ID
     * @param id Принимает ID приоритета
     * @return Возвращает приоритет
     */
    @Override
    public Optional<Priority> findById(int id) {
        return priorityRepository.findById(id);
    }
}
