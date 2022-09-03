package fileuploader.fileupload.repository;

import fileuploader.fileupload.model.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileHolderRepository extends JpaRepository<FileStorage, Long> {
}
