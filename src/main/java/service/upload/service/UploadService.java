package service.upload.service;

import java.util.List;

import service.upload.model.UserData;

public interface UploadService {
	
	public void uploadData(List<UserData> userData);
	
	public List<UserData> getAll();
	
	
	

}
