package ec.edu.dto;

import java.util.Date;
import java.util.List;

import ec.edu.model.MovieBite;
import ec.edu.model.Role;
import ec.edu.model.ScreenSeatClass;
import ec.edu.model.Screens;
import ec.edu.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int userId;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	private Role role;
	private List<Screens> screens;
	private List<MovieBite> movieByte; 

}
