package service.upload.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {

	@JsonIgnore
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty
	@CsvBindByName
	private String userId;
	@JsonProperty
	@CsvBindByName
	private String fullName;
	@JsonProperty
	@CsvBindByName
	private String jobTitle;
	@JsonProperty
	@CsvBindByName
	private String department;
	@JsonProperty
	@CsvBindByName
	private String businessUnit;
	@JsonProperty
	@CsvBindByName
	private String gender;
	@JsonProperty
	@CsvBindByName
	private int age;
	@JsonProperty
	@CsvBindByName
	private String country;
	@JsonProperty
	@CsvBindByName
	private String city;
	
	

}
