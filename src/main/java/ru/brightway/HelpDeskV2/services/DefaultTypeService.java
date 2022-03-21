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

@Service
@AllArgsConstructor
public class DefaultTypeService implements TypeService {
    @Autowired
    private final TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteType(Integer typeId) {
        typeRepository.deleteById(typeId);
    }

    @Override
    public List<Type> findAll() {
        return new ArrayList<>(typeRepository.findAll());
    }

    @Override
    public Optional<Type> findById(int id) {
        return typeRepository.findById(id);
    }
}