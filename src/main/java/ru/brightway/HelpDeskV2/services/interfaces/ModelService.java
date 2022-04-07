package ru.brightway.HelpDeskV2.services.interfaces;

import org.springframework.ui.Model;

import java.security.Principal;

public interface ModelService {
    Model inject(Principal principal, Model model);
}
