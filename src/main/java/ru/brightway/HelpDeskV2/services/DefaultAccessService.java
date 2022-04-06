package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Role;
import ru.brightway.HelpDeskV2.repository.AccessRepository;
import ru.brightway.HelpDeskV2.services.interfaces.AccessService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с доступом пользователей к программе.
 */
@Service
@AllArgsConstructor
public class DefaultAccessService implements AccessService {
    @Autowired
    private final AccessRepository accessRepository;

    /**
     * Метод сохраняет роль в базе. Не используется.
     * @param role Принимает значение роли
     */
    @Override
    public void saveAccess(Role role) {
        accessRepository.save(role);
    }

    /**
     * Метод удаляет роль пользователя. Не используется.
     * @param accessId Принимает ID роли
     */
    @Override
    public void deleteAccess(Integer accessId) {
        accessRepository.deleteById(accessId);
    }

    /**
     * Метод поиска ролей пользователей в базе
     * @return Возвращает коллекцию ролей
     */
    @Override
    public List<Role> findAll() {
        return new ArrayList<>(accessRepository.findAll());
    }

    /**
     * Метод поиска роли по ID
     * @param role Принимает ID роли
     * @return Возвращает роль пользователя
     */
    @Override
    public Optional<Role> findById(int role) {
        return accessRepository.findById(role);
    }
}
