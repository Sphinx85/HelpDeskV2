package ru.brightway.HelpDeskV2.services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Сервис для загрузки файлов
 */
@Service
public class FileService {

    /**
     * Значение параметра пути для загрузки
     */
    public static final String LOGO_DIRECTORY = "src/main/resources/static/upload/logo/";

    /**
     * Метод для загрузки файлов на сервер. Реализация для логотипа. Не используется в стандартной версии.
     * @param file Принимает файл для загрузки
     */
    public void uploadFile(MultipartFile file){
        if (file.isEmpty()){
            return;
        }
        String fileName = "logo.png";
        try {
            Path path = Paths.get(LOGO_DIRECTORY + fileName);
            file.transferTo(path);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
