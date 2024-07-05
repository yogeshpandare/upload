package service.upload.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import service.upload.model.UserData;
import service.upload.service.UploadService;

/**
 * 
 * @author
 *
 */

@RestController
@RequestMapping("/api/v1/")
public class UploadController {

	Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private UploadService uploadService;

	/**
	 * 
	 * @param file
	 * @return message
	 */

	@PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@CrossOrigin
	public ResponseEntity<String> uploadData(@RequestParam(name = "file") MultipartFile file) {
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			logger.info("file recived with the name {}", file.getName());

			CsvToBean<UserData> csvToBean = new CsvToBeanBuilder(reader).withType(UserData.class).build();
			List<UserData> stockData = csvToBean.parse();
			uploadService.uploadData(stockData);
		} catch (IOException e) {
			// written 400 for bad request
			logger.error("Issue while processing request {} ", e.getMessage());
			return new ResponseEntity<String>("Issue while processing request " + e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// written 500 for internal server
			logger.error("Issue while inserting data into db  {} ", e.getMessage());
			return new ResponseEntity<String>("Issue while inserting data into db " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return 201 for content created
		logger.debug("file Uploaded Successfully");
		return new ResponseEntity<String>("file Uploaded Successfully", HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return list of user data
	 */

	@GetMapping("users")
	@CrossOrigin
	public ResponseEntity<?> getAllUserData() {
		List<UserData> users = new ArrayList<UserData>();
		try {
			users = uploadService.getAll()
					.stream().sorted(Comparator.comparing(UserData::getUserId))
					.collect(Collectors.toList());
			
			logger.info("list of users {} ", users);
		} catch (Exception e) {
			// written 500 for internal server
			logger.error("Issue while fetching request from DB {}", e.getMessage());
			return new ResponseEntity<String>("Issue while fetching request from DB" + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return 200 for sucessfull response
		return new ResponseEntity<List<UserData>>(users, HttpStatus.OK);
	}

}
