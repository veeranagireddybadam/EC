package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.UserDTO;
import ec.edu.model.LoginDetails;
import ec.edu.model.User;
import ec.edu.service.UserService;
@CrossOrigin
@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
		User user=userService.createUser(userDTO);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchallusers")
	public ResponseEntity<List<UserDTO>> fetchallusers(){
		List<UserDTO> userDTOs=userService.fetchallusers();
		return new ResponseEntity<>(userDTOs,HttpStatus.OK);

	}
	
	@GetMapping("/fetchoneuser/{userId}")
	public ResponseEntity<UserDTO> getoneuser(@PathVariable int userId){
		UserDTO userDTO=userService.getoneuser(userId);
		return new ResponseEntity<>(userDTO,HttpStatus.OK);

	}
	
	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<User> updateuser(@RequestBody UserDTO userDTO,  @PathVariable int userId){
		User user=userService.updateuser(userDTO,userId);
		return new ResponseEntity<>(user,HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteuser(@PathVariable int userId){
		userService.deleteuser(userId);
		return new ResponseEntity<>("deleted user id"+userId,HttpStatus.OK);
	}
	@PostMapping("/checkuser")
	public ResponseEntity<User> checkUser(@RequestBody LoginDetails loginDetails){
		User user=userService.checkUser(loginDetails.getEmail(),loginDetails.getPassword());
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	

    @GetMapping("/check-email/{email}")
    public ResponseEntity<String> checkEmail(@PathVariable String email) {
        boolean exists = userService.checkEmailExists(email);
        if (exists) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Email is available.", HttpStatus.OK);
        }
    }

	

}
