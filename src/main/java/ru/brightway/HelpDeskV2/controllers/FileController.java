package ru.brightway.HelpDeskV2.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.brightway.HelpDeskV2.services.FileService;
import ru.brightway.HelpDeskV2.services.interfaces.ModelService;

import java.security.Principal;

/**
 * Контроллер для загрузки файлов на сервер
 */
@Controller
@Data
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ModelService modelService;

    /**
     * Метод для входа на страницу загрузки файлов на сервер
     * @return Возвращает страницу загрузки
     */
    @GetMapping("/admin/upload")
    public String uploadFilePage(Principal principal, Model model){
        modelService.inject(principal,model);
        return "upload";
    }

    /**
     * Метод загрузки файлов
     * @param file Принимает файл
     * @return После добавления файла на сервер переадресовывает на главную страницу
     */
    @PostMapping("/admin/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        fileService.uploadFile(file);
        return "redirect:/";
    }
}
