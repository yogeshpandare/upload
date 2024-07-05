package service.upload.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.upload.model.UserData;
import service.upload.repository.UploadRepository;
import service.upload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

	@Autowired
	private UploadRepository uploadRepository;

	/**
	 * it will save all the user data into h2 db
	 */
	@Override
	public void uploadData(List<UserData> userData) {
		
		logger.info("uploaddata method is invoked");
		uploadRepository.saveAll(userData);

	}

	/**
	 * it will retrived all the users from h2 db
	 */
	@Override
	public List<UserData> getAll() {
		logger.info("getAll user data  method is invoked");
		List<UserData> users = (List<UserData>) uploadRepository.findAll();
		return users;
	}

}
