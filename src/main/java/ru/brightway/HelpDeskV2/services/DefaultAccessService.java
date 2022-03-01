package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Access;
import ru.brightway.HelpDeskV2.repository.AccessRepository;
import ru.brightway.HelpDeskV2.services.interfaces.AccessService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultAccessService implements AccessService {

    private final AccessRepository accessRepository;
    @Override
    public Access saveAccess(Access access) {
        return accessRepository.save(access);
    }

    @Override
    public void deleteAccess(Integer accessId) {
        accessRepository.deleteById(accessId);
    }

    @Override
    public List<Access> findAll() {
        return new ArrayList<>(accessRepository.findAll());
    }
}
