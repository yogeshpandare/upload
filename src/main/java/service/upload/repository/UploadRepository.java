package service.upload.repository;

import org.springframework.data.repository.CrudRepository;

import service.upload.model.UserData;

public interface UploadRepository extends CrudRepository<UserData, Long> {

}
