package ec.edu.dto;


import ec.edu.model.Movie;
import lombok.Data;

@Data
public class CrewAndCastDTO {
	
	private int member_id;
	private String name;
	private String role;
	private String member_type;
	private String department;
	private String photo_url;
	
	private Movie movie;
	
}