package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.model.Role;
import ec.edu.service.RoleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/createRole")
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		Role roleModel2=roleService.createRole(role);
		return new ResponseEntity<>(roleModel2, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchallroles")
	public ResponseEntity<List<Role>> getallroles() {
		List<Role> roles=roleService.getallroles();
		return new ResponseEntity<>(roles,HttpStatus.OK);
	}
	
}
