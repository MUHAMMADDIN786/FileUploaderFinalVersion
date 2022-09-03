package fileuploader.fileupload.controller;

import fileuploader.fileupload.helper.UploadHelper;

import fileuploader.fileupload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fileuploader.fileupload.model.FileStorage;
import fileuploader.fileupload.repository.FileHolderRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class FileStorageController {
    private final Path root = Paths.get("files");
    @Autowired
    private UploadHelper uploadHelper;
    @Autowired
    private StorageService storageService;

    @Autowired
    private FileHolderRepository fileHolderRepository;

    @RequestMapping("/")
    public String getUserForm() {
        return "userForm";
    }

    @PostMapping("uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("include file");
            }
            String fileName = UUID.randomUUID() + " " + file.getOriginalFilename();

            boolean successStatus = uploadHelper.uploadFile(file, fileName);
            if (successStatus) {
                String fileURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName).toUriString();
                FileStorage storedFile = fileHolderRepository.save(new FileStorage(fileName, fileURL));
                return ResponseEntity.ok(storedFile);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
