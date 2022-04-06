package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Type;
import ru.brightway.HelpDeskV2.repository.TypeRepository;
import ru.brightway.HelpDeskV2.services.interfaces.TypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с типами заявок. Классификатор.
 */
@Service
@AllArgsConstructor
public class DefaultTypeService implements TypeService {
    @Autowired
    private final TypeRepository typeRepository;

    /**
     * Метод сохранения типа
     * @param type Принимает тип
     * @return Возвращает тип
     */
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * Метод удаления типа. Удаляет из базы! Может вызывать ошибки! Классификатор рекомендуется заполнить
     * перед использованием программы.
     * @param typeId Принимает ID типа
     */
    @Override
    public void deleteType(Integer typeId) {
        typeRepository.deleteById(typeId);
    }

    /**
     * Метод поиска всех типов
     * @return Возвращает коллекцию типов
     */
    @Override
    public List<Type> findAll() {
        return new ArrayList<>(typeRepository.findAll());
    }

    /**
     * Метод поиска типа по ID
     * @param id Принимает ID типа
     * @return Возвращает тип
     */
    @Override
    public Optional<Type> findById(int id) {
        return typeRepository.findById(id);
    }
}
