package fileuploader.fileupload;

import fileuploader.fileupload.service.StorageService;
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileSystemUtils;

import javax.annotation.Resource;

@SpringBootApplication
public class FileuploadApplication implements CommandLineRunner {
    @Resource
    StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
