package ru.brightway.HelpDeskV2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.brightway.HelpDeskV2.Entites.Role;
import ru.brightway.HelpDeskV2.repository.AccessRepository;
import ru.brightway.HelpDeskV2.services.interfaces.AccessService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultAccessService implements AccessService {

    private final AccessRepository accessRepository;
    @Override
    public Role saveAccess(Role role) {
        return accessRepository.save(role);
    }

    @Override
    public void deleteAccess(Integer accessId) {
        accessRepository.deleteById(accessId);
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(accessRepository.findAll());
    }
}
