package ec.edu.service;

import java.util.List;

import ec.edu.dto.UserDTO;
import ec.edu.model.User;

public interface UserService {

	User createUser(UserDTO userDTO);


	List<UserDTO> fetchallusers();


	UserDTO getoneuser(int userId);


	User updateuser(UserDTO userDTO, int userId);


	void deleteuser(int userId);


	User checkUser(String email, String password);


    boolean checkEmailExists(String email);


//	User getUserById(int userId);

}
