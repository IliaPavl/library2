package com.example.libra.servise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String saveFile(MultipartFile file, int isWhat) throws IOException {
        if(isWhat==1)
            return getStandartBookFileName();

        String nameFile=null;
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            return resultFilename;
        }
        return nameFile;
    }

    protected String getStandartBookFileName(){
        return "standartBook.png";
    }

    protected String getStandartProfileFileName(){
        return "user.png";
    }

    protected String getStandartProfileFontName(){
        return "fon.png";
    }

}
