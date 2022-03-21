package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.brightway.HelpDeskV2.Entites.Priority;
import ru.brightway.HelpDeskV2.Entites.Role;
import ru.brightway.HelpDeskV2.Entites.Type;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Контроллер обработки запросов администратора
 */

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
@Data
@RequestMapping("/admin")
@CrossOrigin
public class AdminPanel {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Метод отображения панели администратора.
     * @return Возвращает страницу adminPanel.html
     */
    @GetMapping("/panel")
    public String adminPanel(){
        return "adminPanel";
    }

    /**
     * Метод отрисовки списка всех пользователей.
     * @param model Входным параметром является модель, в которую помещается коллекция пользователей из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allUsers")
    public String allUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "allLists";
    }

    /**
     * Метод отрисовки списка всех заявок.
     * @param model Входным параметром является модель, в которую помещается коллекция заявок из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allMessages")
    public String allMessages(Model model){
        model.addAttribute("messages", messageService.findAll());
        return "allLists";
    }

    /**
     * Метод отрисовки списка всех типов заявок. Классификатор.
     * @param model Входным параметром является модель, в которую помещается коллекция типов из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allTypes")
    public String allTypes(Model model){
        model.addAttribute("types", typeService.findAll());
        return "allLists";
    }

    /**
     * Метод отрисовки списка всех приоритетов заявок.
     * @param model Входным параметром является модель, в которую помещается коллекция приоритетов из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allPriorities")
    public String allPriorities(Model model){
        model.addAttribute("priorities", priorityService.findAll());
        return "allLists";
    }

    /**
     * Метод отрисовки списка всех ролей пользователей.
     * @param model Входным параметром является модель, в которую помещается коллекция ролей из БД
     * @return Возвращает страницу allLists.html
     */
    @GetMapping("/allRoles")
    public String allRoles(Model model){
        model.addAttribute("roles", accessService.findAll());
        return "allLists";
    }

    /**
     * Метод создает модель того типа, которому необходимо заполнить форму.
     * @param typeModel В адресной строке передается параметр типа модели
     * @param model В модель подается отправленный тип
     * @return Возвращает страницу, содержащую формы form.html
     */
    @GetMapping("/form/{typeModel}")
    public String form(@PathVariable(name = "typeModel") String typeModel,Model model){
        switch (typeModel){
            case "user":
                model.addAttribute("user",new User());

            case "type":
                model.addAttribute("type",new Type());

            case "priority":
                model.addAttribute(new Priority());

            case "role":
                model.addAttribute(new Role());
        }
        return "form";
    }

    /**
     * Метод создания нового пользователя.
     * @param first_name Входной параметр принимает имя пользователя
     * @param last_name Входной параметр принимает фамилию пользователя
     * @param username Входной параметр принимает логин пользователя
     * @param password Входной параметр принимает пароль пользователя. Пароль хэшируется.
     * @param role Входной параметр принимает роль, назначенную администратором.
     * @return Возвращает переадресацию на список всех пользователей
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @PostMapping("/newUser")
    public String newUser(@ModelAttribute(name = "first_name") String first_name,
                          @ModelAttribute(name = "last_name") String last_name,
                          @ModelAttribute(name = "username") String username,
                          @ModelAttribute(name = "password") String password,
                          @ModelAttribute(name = "role") int role){
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(accessService.findById(role).get());
        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/allUsers";
    }

    /**
     * Метод создания нового типа заявки. Является классификатором заявок. Настраивается администратором.
     * @param id Входной параметр принимает id типа. Может являться любым уникальным числом. Не является последовательностью.
     * @param description Входной параметр принимает описание. Фактическое название пункта классификатора
     * @return Возвращает переадресацию на список всех типов
     */
    @PostMapping("/newType")
    public String newType(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Type type = new Type();
        type.setId(id);
        type.setDescription(description);
        typeService.saveType(type);
        return "redirect:/admin/allTypes";
    }

    /**
     * Метод создания новых приоритетов
     * @param id Входной параметр принимает id приоритета. Может являться любым уникальным числом. Не является последовательностью.
     * @param description Входной параметр принимает описание. Фактическое название пункта приоритета
     * @return Возвращает переадресацию на список всех приоритетов
     */
    @PostMapping("/newPriority")
    public String newPriority(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Priority priority = new Priority();
        priority.setId(id);
        priority.setDescription(description);
        priorityService.savePriority(priority);
        return "redirect:/admin/allPriorities";
    }

    /**
     * Метод создания новых ролей пользователя
     * @param id Входной параметр принимает id роли пользователя. Может являться любым уникальным числом. Не является последовательностью.
     * @param description Входной параметр принимает название роли. Формат строго определен.
     *                    Перед названием роли необходимо ставить префикс 'ROLE_' а само название передавать
     *                    в верхнем регистре. Например: ROLE_USER
     * @return Возвращает переадресацию на список всех ролей пользователей
     */
    @PostMapping("/newRole")
    public String newRole(@ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Role role = new Role();
        role.setId(id);
        role.setRole(description);
        accessService.saveAccess(role);
        return "redirect:/admin/allRoles";
    }

    /**
     * Метод обновления данных о пользователе
     * @param id Производится поиск пользователя в базе по id.
     * @param first_name Входной параметр принимает имя пользователя
     * @param last_name Входной параметр принимает фамилию пользователя
     * @param username Входной параметр принимает логин пользователя
     * @param password Входной параметр принимает пароль пользователя. Пароль хэшируется.
     * @param role Входной параметр принимает роль, назначенную администратором.
     * @return Возвращает переадресацию на список всех пользователей
     */
    @PostMapping("upUser/{id}")
    public String upUser(@PathVariable("id") int id,
                          @ModelAttribute(name = "first_name") String first_name,
                          @ModelAttribute(name = "last_name") String last_name,
                          @ModelAttribute(name = "username") String username,
                          @ModelAttribute(name = "password") String password,
                          @ModelAttribute(name = "role") int role){
        Set<Role> roles = new LinkedHashSet<>();
        roles.add(accessService.findById(role).get());
        User user = userService.findById(id).get();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin/allUsers";
    }

    /**
     * Метод обновления данных о типе заявки. Обновление классификатора
     * @param idType Производится поиск типа заявки в базе по id.
     * @param id Входной параметр принимает id типа заявки
     * @param description Входной параметр принимает описание типа заявки
     * @return Возвращает переадресацию на список всех типов
     */
    @PostMapping("/upType/{id}")
    public String upType(@PathVariable("id") int idType,
                          @ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Type type = typeService.findById(idType).get();
        type.setId(id);
        type.setDescription(description);
        typeService.saveType(type);
        return "redirect:/admin/allTypes";
    }

    /**
     * Метод обновления данных о приоритете заявки.
     * @param idPriority Производится поиск приоритета заявки в базе по id.
     * @param id Входной параметр принимает id приоритета заявки
     * @param description Входной параметр принимает описание приоритета заявки
     * @return Возвращает переадресацию на список всех приоритетов
     */
    @PostMapping("/upPriority/{id}")
    public String upPriority(@PathVariable("id") int idPriority,
                              @ModelAttribute(name = "id") int id,
                              @ModelAttribute(name = "description") String description){
        Priority priority = priorityService.findById(idPriority).get();
        priority.setId(id);
        priority.setDescription(description);
        priorityService.savePriority(priority);
        return "redirect:/admin/allPriorities";
    }

    /**
     * Метод обновления данных о роли пользователя.
     * @param idRole Производится поиск роли пользователя в базе по id.
     * @param id Входной параметр принимает id роли пользователя
     * @param description Входной параметр принимает название роли. Формат строго определен.
     *                    Перед названием роли необходимо ставить префикс 'ROLE_' а само название передавать
     *                    в верхнем регистре. Например: ROLE_USER
     * @return Возвращает переадресацию на список всех ролей пользователей
     */
    @PutMapping("/upRole/{id}")
    public String upRole(@PathVariable("id") int idRole,
                         @ModelAttribute(name = "id") int id,
                          @ModelAttribute(name = "description") String description){
        Role role = accessService.findById(idRole).get();
        role.setId(id);
        role.setRole(description);
        accessService.saveAccess(role);
        return "redirect:/admin/allRoles";
    }
}