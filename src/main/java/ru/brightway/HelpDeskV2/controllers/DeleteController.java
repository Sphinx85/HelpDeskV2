package ru.brightway.HelpDeskV2.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.brightway.HelpDeskV2.Entites.User;
import ru.brightway.HelpDeskV2.services.interfaces.*;

/**
 * Контроллер удаления записей из базы. Доступен только администратору.
 */

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
@RequestMapping("/admin/delete")
@Data
@AllArgsConstructor
@CrossOrigin
public class DeleteController {
    //Блок инъекций необходимых сервисов
    @Autowired
    private UserService userService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private TypeService typeService;

    /**
     * Метод удаления пользователя по id
     * @param id Параметр из строки запроса ищет пользователя по id
     * @return Возвращает переадресацию на список пользователей
     */
    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id){
        User user = userService.findById(id).get();
        user.setEnable(false);
        userService.update(user);
        return "redirect:/admin/allUsers";
    }

    /**
     * Метод удаления роли пользователя по id
     * @param id Параметр из строки запроса ищет роль пользователя по id
     * @return Возвращает переадресацию на список ролей
     */
    @GetMapping("/role/{id}")
    public String deleteRole(@PathVariable(name = "id") Integer id){
        accessService.deleteAccess(id);
        return "redirect:/admin/allRoles";
    }

    /**
     * Метод удаления заявки по id
     * @param id Параметр из строки запроса ищет заявку по id
     * @return Возвращает переадресацию на список заявок
     */
    @GetMapping("/message/{id}")
    public String deleteMessage(@PathVariable(name = "id") Integer id){
        messageService.deleteMessage(id);
        return "redirect:/admin/allMessages";
    }

    /**
     * Метод удаления приоритета заявки по id
     * @param id Параметр из строки запроса ищет приоритет заявки по id
     * @return Возвращает переадресацию на список приоритетов
     */
    @GetMapping("/priority/{id}")
    public String deletePriority(@PathVariable(name = "id") Integer id){
        priorityService.deletePriority(id);
        return "redirect:/admin/allPriorities";
    }

    /**
     * Метод удаления типа заявки по id
     * @param id Параметр из строки запроса ищет тип заявки по id
     * @return Возвращает переадресацию на список типов
     */
    @GetMapping("/type/{id}")
    public String deleteType(@PathVariable(name = "id") Integer id){
        typeService.deleteType(id);
        return "redirect:/admin/allTypes";
    }
}