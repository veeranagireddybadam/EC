package ec.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.UserDTO;
import ec.edu.model.Role;
import ec.edu.model.User;
import ec.edu.repository.RoleRepository;
import ec.edu.repository.UserRepository;

@Service
public class UserServiceImp  implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

   

	@Override
	public User createUser(UserDTO userDTO) {
		User user=new User();
		
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setPassword(userDTO.getPassword());
//		user.setCreatedAt(userDTO.getCreatedAt());
//		user.setUpdatedAt(userDTO.getUpdatedAt());
		Role  role=roleRepository.findById(userDTO.getRole().getRoleId()).orElse(null);
		user.setRole(role);
		
		return userRepository.save(user);
	}

	

	@Override
	public List<UserDTO> fetchallusers() {
		// TODO Auto-generated method stub
		ArrayList<UserDTO> userdtos=new ArrayList<>();
		List<User> users=userRepository.findAll();
		
		for (User user:users) {
			UserDTO userDTO=new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setUserName(user.getUserName());
			userDTO.setEmail(user.getEmail());
			userDTO.setPhone(user.getPhone());
			userDTO.setPassword(user.getPassword());
//			userDTO.setCreatedAt(user.getCreatedAt());
//			userDTO.setUpdatedAt(user.getUpdatedAt());
			userDTO.setRole(user.getRole());
			
			userdtos.add(userDTO);
			
		}
		return userdtos;
	}



	@Override
	public UserDTO getoneuser(int userId) {
		User user=userRepository.findById(userId).orElse(null);
		UserDTO userDTO=new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setPassword(user.getPassword());
//		userDTO.setCreatedAt(user.getCreatedAt());
//		userDTO.setUpdatedAt(user.getUpdatedAt());
		userDTO.setRole(user.getRole());
		
		return userDTO;
	}



	@Override
	public User updateuser(UserDTO userDTO, int userId) {
		// TODO Auto-generated method stub
		User oldUser=userRepository.findById(userId).orElse(null);
		oldUser.setUserId(userDTO.getUserId());
		oldUser.setUserName(userDTO.getUserName());
		oldUser.setEmail(userDTO.getEmail());
		oldUser.setPhone(userDTO.getPhone());
		oldUser.setPassword(userDTO.getPassword());
//		oldUser.setCreatedAt(userDTO.getCreatedAt());
//		oldUser.setUpdatedAt(userDTO.getUpdatedAt());
		oldUser.setRole(userDTO.getRole());
		
		return userRepository.save(oldUser);
	}



	@Override
	public void deleteuser(int userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		
	}
	
	@Override
	public User checkUser(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email,password);	
	}

	 @Override
	    public boolean checkEmailExists(String email) {
	        return userRepository.existsByEmail(email);
	    }



}
