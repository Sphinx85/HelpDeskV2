package ru.brightway.HelpDeskV2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.brightway.HelpDeskV2.services.FileService;

/**
 * Контроллер для загрузки файлов на сервер
 */
@Controller
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * Метод для входа на страницу загрузки файлов на сервер
     * @return Возвращает страницу загрузки
     */
    @GetMapping("/admin/upload")
    public String uploadFilePage(){
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
