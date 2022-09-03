package fileuploader.fileupload.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadHelper {
    private final Path root = Paths.get("files");
    public UploadHelper() throws IOException {
    }


    public boolean uploadFile(MultipartFile multipartFile, String fileName) {
        boolean flag = false;
        try {
            Files.copy(multipartFile.getInputStream(), this.root.resolve(fileName));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return flag;
    }

}
