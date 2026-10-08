package ec.edu.service;


import java.util.List;

import ec.edu.model.Role;

public interface RoleService {

	Role createRole(Role role);

	List<Role> getallroles();
	
	

}
