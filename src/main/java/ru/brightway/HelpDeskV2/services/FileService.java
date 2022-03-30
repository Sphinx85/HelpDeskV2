package ru.brightway.HelpDeskV2.services;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Сервис для загрузки файлов
 */
@Service
public class FileService {

    /**
     * Значение параметра пути для загрузки
     */
    @Value("${app.upload.dir}")
    public String uploadDir;

    /**
     * Метод для загрузки файлов на сервер
     * @param file Принимает файл для загрузки
     */
    public void uploadFile(MultipartFile file){
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
