package kz.iitu.restapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Could not save image file:" + fileName, ioe);
        }
    }
    public static void deleteFile(String deleteDir) throws IOException{
        Path deletePath = Paths.get(deleteDir);
        File file = new File(deleteDir);
        if(Files.exists(deletePath)){
            deleteSubFiles(file);
            Files.delete(deletePath);
        }
    }
    private static void deleteSubFiles(File file){
        for(File subfile : file.listFiles()){
            if(subfile.isDirectory())
                deleteSubFiles(subfile);
            subfile.delete();
        }
    }
}
