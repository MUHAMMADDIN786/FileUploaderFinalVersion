package fileuploader.fileupload.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tbl_files")
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String fileURL;

    public FileStorage() {
    }

    public FileStorage(Long id, String fileName, String fileURL) {
        this.id = id;
        this.fileName = fileName;
        this.fileURL = fileURL;
    }

    public FileStorage(String fileName, String fileURL) {
        this.fileName = fileName;
        this.fileURL = fileURL;
    }
}
