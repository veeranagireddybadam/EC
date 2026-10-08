package ec.edu.dto;

import java.util.Date;

import ec.edu.model.User;
import lombok.Data;

@Data
public class MovieBiteDTO {
	private Integer biteId;
	private String  biteName;
	private Double bitePrice;
	private String moviebiteUrl;
	
	private  int createdBy;
	private int updatedBy;
//	
//	private Date createdDate;
//	
//	private Date updatedDate;
//	
	private User user;
}